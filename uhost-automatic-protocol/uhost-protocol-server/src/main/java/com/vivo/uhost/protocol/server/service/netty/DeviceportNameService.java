/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.server.service.netty;

import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.thread.ThreadDistribution;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.message.Message;
import com.vivo.uhost.message.model.DevicePortNameChangeReq;
import com.vivo.uhost.message.model.DeviceStateChangeReq;
import com.vivo.uhost.protocol.core.service.IService;
import com.vivo.uhost.protocol.redis.RedisDao;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
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
@Service("devicePortNameService")
public class DeviceportNameService implements IService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceportNameService.class);

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

            final DevicePortNameChangeReq devicePortNameChangeReq = JsonUtils.toObject(msg, DevicePortNameChangeReq.class);
            if (devicePortNameChangeReq == null || StringUtil.isBlank(devicePortNameChangeReq.getToken())) {
                return null;
            }

            String token = devicePortNameChangeReq.getToken();
            String tokenKey = RedisKeyUtils.getUhostKey(token);
            Map<String, String> mapEntriy = redisDao.entries(tokenKey);
            if (mapEntriy == null || mapEntriy.isEmpty()) {
                logger.warn("ping failed. token: {}", token);
                return Message.INVALID_TOKEN_MESSAGE;
            }

            ThreadDistribution.getInstance().submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    deviceChangeService.uhostDeviceChanged(devicePortNameChangeReq);
                    //通知浏览器刷新设备列表
                    Thread.sleep(3000);
                    RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
                    redisQProducer.sendPageRefresh(Constants.REFRESH_DEVICE);//这里和前端对应，消息体中包含device
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
