package com.vivo.uhost.protocol.server.service.netty;

import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.message.Message;
import com.vivo.uhost.message.model.TokenMsg;
import com.vivo.uhost.protocol.core.service.IService;
import com.vivo.uhost.protocol.redis.RedisDao;
import com.vivo.uhost.protocol.server.service.IUhostStateChangeService;
import com.vivo.uhost.protocol.server.utils.RedisKeyUtils;
import com.vivo.uhost.protocol.server.utils.ServerConstants;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-6-24
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
@Service("uhostPingService")
public class UhostPingService implements IService {

    private static final Logger logger = LoggerFactory.getLogger(UhostPingService.class);

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private IUhostStateChangeService uhostStateChangeService;

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
            TokenMsg tokenMsg = JsonUtils.toObject(msg, TokenMsg.class);
            if (tokenMsg == null || StringUtil.isBlank(tokenMsg.getToken())) {
                return null;
            }
            String token = tokenMsg.getToken();
            String tokenKey = RedisKeyUtils.getUhostKey(token);
            Map<String, String> mapEntriy = redisDao.entries(tokenKey);
            if (mapEntriy == null || mapEntriy.isEmpty()) {
                logger.warn("ping failed. token: {}", token);
                returnMsg = Message.INVALID_TOKEN_MESSAGE;
                return receiveMsg;
            }

            String uidentify = mapEntriy.get("uidentify");
            Channel channel = ctx.channel();
            //新接入进来的uhost
            if (!ServerConstants.MAP_TOKEN_CHANNEL.containsKey(token)) {
                uhostStateChangeService.uhostStateChange(token, uidentify, 2);
            } else {
                Channel existChannel = ServerConstants.MAP_TOKEN_CHANNEL.get(token);
                if (!channel.equals(existChannel)) {
                    existChannel.close();
                    logger.info("client reconnect! {}=>{}", existChannel.hashCode(), channel.hashCode());
                }
            }
            ServerConstants.MAP_CHANNEL_CHANNELINFO.get(channel).setPingTime(System.currentTimeMillis());
            ServerConstants.MAP_CHANNEL_CHANNELINFO.get(channel).setToken(token);
            ServerConstants.MAP_TOKEN_CHANNEL.put(token, channel); //replace with original channel which is active or new reconnected channel
            returnMsg = Message.PING_ACK_MESSAGE;
            logger.info("ping success! token: {}, MAP_TOKENS size: {}", token, ServerConstants.MAP_TOKEN_CHANNEL.size());
        } catch (Exception e) {
            logger.error("process ping msg error!", e);
        }
        return returnMsg;
    }
}
