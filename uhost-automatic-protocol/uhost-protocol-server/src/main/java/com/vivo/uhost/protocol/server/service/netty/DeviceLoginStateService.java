/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.server.service.netty;

import com.vivo.uhost.comm.thread.ThreadDistribution;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.message.Message;
import com.vivo.uhost.message.model.DeviceStateChangeReq;
import com.vivo.uhost.protocol.core.service.IService;
import com.vivo.uhost.protocol.redis.RedisDao;
import com.vivo.uhost.protocol.server.service.IDeviceChangeService;
import com.vivo.uhost.protocol.server.utils.RedisKeyUtils;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author huangxiaoqun  2018/3/28 18:03
 * @version 1.0
 * @description 设备登录登出服务
 */
@Service("deviceLoginStateService")
public class DeviceLoginStateService implements IService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceLoginStateService.class);

    @Autowired
    private IDeviceChangeService deviceChangeService;

    @Autowired
    private RedisDao redisDao;

    @Override
    public Message processMsg(ChannelHandlerContext ctx, Message receiveMsg) {
        try {

            if (ctx == null || receiveMsg == null) {
                return null;
            }

            String msg = receiveMsg.getMsg();
            if (StringUtil.isBlank(msg)) {
                return null;
            }

            final DeviceStateChangeReq deviceStateChangeReq = JsonUtils.toObject(msg, DeviceStateChangeReq.class);
            if (deviceStateChangeReq == null || StringUtil.isBlank(deviceStateChangeReq.getToken())) {
                return null;
            }

            String token = deviceStateChangeReq.getToken();
            String tokenKey = RedisKeyUtils.getUhostKey(token);
            Map<String, String> mapEntriy = redisDao.entries(tokenKey);
            if (mapEntriy == null || mapEntriy.isEmpty()) {
                logger.warn("ping failed. token: {}", token);
                return Message.INVALID_TOKEN_MESSAGE;
            }

            ThreadDistribution.getInstance().submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    deviceChangeService.deviceChanged(deviceStateChangeReq);
                    return null;
                }
            });
            return Message.PING_ACK_MESSAGE;
        } catch (Exception ex) {
            logger.error("process device login state changed failed", ex);
        }
        return null;
    }
}
