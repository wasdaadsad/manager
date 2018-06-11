package com.vivo.uhost.protocol.server.service.netty;

import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.DateUtils;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.UhostInfo;
import com.vivo.uhost.message.Message;
import com.vivo.uhost.message.model.TokenMsg;
import com.vivo.uhost.protocol.core.service.IService;
import com.vivo.uhost.protocol.redis.RedisDao;
import com.vivo.uhost.protocol.server.utils.RedisKeyUtils;
import com.vivo.uhost.service.IDeviceService;
import com.vivo.uhost.service.IUhostInfoService;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务器redis无注册信息，则不允许登录，若有注册信息，更新redis中的登录信息，并且针对token一致哈希计算返回集群中一个server地址。
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
@Service("uhostLoginService")
public class UhostLoginService implements IService {
    private static final Logger logger = LoggerFactory.getLogger(UhostLoginService.class);

    @Autowired
    private RedisDao redisDao;

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
                logger.warn("login failed. token: {}", token);
                returnMsg = Message.INVALID_TOKEN_MESSAGE;
            } else {
                /*
                 * 1、从redis缓存中取出该token对应的uidentify
                 * 2、通过uidentify找到Uhsot
                 * 3、由于Uhost的id外键关联设备的UhostId字段，所有可以找到之前的设备
                 * */
                String uidentify = mapEntriy.get("uidentify");
                IUhostInfoService uhostInfoService = SpringBeanUtil.getBean("uhostInfoService", IUhostInfoService.class);
                UhostInfo uhostInfo = uhostInfoService.findByIdentify(uidentify);
                IDeviceService deviceService = SpringBeanUtil.getBean("deviceService", IDeviceService.class);
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.setUhostId(uhostInfo.getId());
                List<DeviceInfo> deviceInfoList =  deviceService.selectListByUhostId(deviceInfo);
                if(deviceInfoList != null){
                    for (DeviceInfo deviceInfoItem  : deviceInfoList){
                        //更新设备的token为新token
                        deviceInfoItem.setUhost(token);
                        deviceService.updateUhost(deviceInfoItem);
                    }
                }
                redisDao.hashAdd(tokenKey, "lastLogin", DateUtils.format(new Date(), DateUtils.FORMAT_YMDHMS));
                returnMsg = new Message(Constants.CMD_TYPE_LOGIN_ACK, JsonUtils.toJson(new TokenMsg(token)));
            }
        } catch (Exception e) {
            logger.error("process login msg error!", e);
        }
        return returnMsg;
    }
}
