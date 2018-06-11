package com.vivo.uhost.protocol.server.mq;

import com.vivo.uhost.comm.thread.ThreadDistribution;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.message.model.DeviceStateChangeReq;
import com.vivo.uhost.protocol.server.service.IDeviceChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.concurrent.Callable;

/**
 * uhost发送设备状态变更到redis指定的topic，pushServer进行消费
 */
public class TopicDeviceLoginStateConsumer implements MessageListener {

       private static Logger logger = LoggerFactory.getLogger(TopicDeviceLoginStateConsumer.class);

    @Autowired
    private IDeviceChangeService deviceChangeService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        if (message == null || null == message.getBody()) {
            return;
        }
        try {
            String msg = message.toString();
            final DeviceStateChangeReq changeReq = JsonUtils.toObject(msg, DeviceStateChangeReq.class);
            ThreadDistribution.getInstance().submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    deviceChangeService.deviceChanged(changeReq);
                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("handle push message error!", e);
        }
    }


}
