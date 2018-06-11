package com.vivo.uhost.protocol.server.service.netty;

import com.alibaba.fastjson.JSONObject;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.DateUtils;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.comm.util.UUIDUtils;
import com.vivo.uhost.dal.entity.ServerNode;
import com.vivo.uhost.dal.entity.UhostInfo;
import com.vivo.uhost.message.Message;
import com.vivo.uhost.message.model.TokenMsg;
import com.vivo.uhost.message.model.UhostModel;
import com.vivo.uhost.protocol.core.service.IService;
import com.vivo.uhost.protocol.redis.RedisDao;
import com.vivo.uhost.protocol.server.utils.RedisKeyUtils;
import com.vivo.uhost.protocol.server.utils.ServerConstants;
import com.vivo.uhost.service.IServerNodeService;
import com.vivo.uhost.service.IUhostInfoService;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 注册逻辑。
 *
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-6-25
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
@Service("uhostRegisterService")
public class UhostRegisterService implements IService {
    private static final Logger logger = LoggerFactory.getLogger(UhostRegisterService.class);

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private IServerNodeService serverNodeService;

    @Autowired
    private IUhostInfoService uhostInfoService;

    private volatile static Integer serverId;

    @Override
    public Message processMsg(ChannelHandlerContext ctx, Message receiveMsg) {
        Message returnMsg = null;
        try {
            if (ctx == null || receiveMsg == null) {
                return null;
            }
            String msg = receiveMsg.getMsg();
            if (StringUtil.isBlank(msg)) {
                return null;
            }
            UhostModel uhostModel = JsonUtils.toObject(msg, UhostModel.class);
            if (uhostModel == null
                    || StringUtil.isBlank(uhostModel.getIpAdress())
                    || StringUtil.isBlank(uhostModel.getUidentify())) {
                return null;
            }

            UhostInfo uhostInfo = uhostInfoService.findByIdentify(uhostModel.getUidentify());
            if (serverId == null) {
                String serverInfo = redisDao.getHashValue(Constants.PUSH_SERVER, ServerConstants.SERVER_HASH);
                if (StringUtils.isEmpty(serverInfo)) {
                    return null;
                }
                JSONObject jsonObject = JSONObject.parseObject(serverInfo);
                String serverAddress = jsonObject.getString("serverAddress");
                Integer port = jsonObject.getInteger("tcpPort");
                ServerNode serverNode = new ServerNode();
                serverNode.setServerAddress(serverAddress);
                serverNode.setTcpPort(port);
                ServerNode node = serverNodeService.getNodeByAddress(serverNode);
                serverId = node.getId();
            }

            String token = UUIDUtils.getUUID();

            if (uhostInfo == null) {
                uhostInfo = new UhostInfo(uhostModel.getFactory(), uhostModel.getWorkShorp(), uhostModel.getAgingRoom(),
                        uhostModel.getAgingRack(), uhostModel.getUidentify(), uhostModel.getIpAdress());
                uhostInfo.setServerId(serverId);
                uhostInfo.setToken(token);
                uhostInfo.setRemark("Login time:" + DateUtils.format(new Date(), DateUtils.DEFAULT_FORMAT));
                uhostInfoService.addUhost(uhostInfo);
            } else {
                //如果是已經存在token則先刪除然後重新生成
                String oldToken = uhostInfo.getToken();
                if (StringUtil.isNotBlank(oldToken)) {
                    redisDao.del(RedisKeyUtils.getUhostKey(oldToken));
                }
                uhostInfo.setToken(token);
                uhostInfo.setServerId(serverId);
                uhostInfoService.editUhost(uhostInfo);
            }

            String tokenKey = RedisKeyUtils.getUhostKey(token);
            redisDao.hashAdd(tokenKey, "createdTime", DateUtils.format(new Date(), DateUtils.FORMAT_YMDHMS));
            redisDao.hashAdd(tokenKey, "uidentify", uhostInfo.getUidentify());
            redisDao.hashAdd(tokenKey, "ipadress", uhostInfo.getIpAdress());
            returnMsg = new Message(Constants.CMD_TYPE_REGISTER_ACK, JsonUtils.toJson(new TokenMsg(token)));
        } catch (Exception e) {
            logger.error("process register msg error!", e);
        }
        return returnMsg;
    }
}
