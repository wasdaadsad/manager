package com.vivo.uhost.protocol.server.mq;

import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.thread.ThreadDistribution;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.message.model.DevicePortNameChangeReq;
import com.vivo.uhost.message.model.DeviceStateChangeReq;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
import com.vivo.uhost.protocol.server.service.IDeviceChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.concurrent.Callable;

/**
 *
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/4/9
 */
public class TopicDevicePortNameStateConsumer implements MessageListener {
    private static Logger logger = LoggerFactory.getLogger(TopicDevicePortNameStateConsumer.class);
    @Autowired
    private IDeviceChangeService deviceChangeService;
    @Override
    public void onMessage(Message message, byte[] bytes) {
        if (message == null || null == message.getBody()) {
            return;
        }
        try {
            String msg = message.toString();
            final DevicePortNameChangeReq changeReq = JsonUtils.toObject(msg, DevicePortNameChangeReq.class);
            logger.info("DevicePortNameChangeReq:"+changeReq.toString());
            ThreadDistribution.getInstance().submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    deviceChangeService.uhostDeviceChanged(changeReq);
                    //通知浏览器刷新任务状态和流程状态列表
                    Thread.sleep(3000);
                    RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
                    redisQProducer.sendPageRefresh(Constants.REFRESH_DEVICE);//这里和前端对应，消息体中包含device
                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("handle push message error!", e);
        }
    }
}
