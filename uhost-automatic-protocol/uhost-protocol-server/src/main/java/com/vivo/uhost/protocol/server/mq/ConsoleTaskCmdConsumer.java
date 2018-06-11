/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.server.mq;

import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.core.mq.PushNotice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author huangxiaoqun  2018/3/29 14:51
 * @version 1.0
 * @description web端推送任务给push Server
 */
public class ConsoleTaskCmdConsumer implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(TopicDeviceLoginStateConsumer.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        if (message == null || null == message.getBody()) {
            return;
        }
        try {
            String msg = message.toString();
            final PushNotice pushNotice = JsonUtils.toObject(msg, PushNotice.class);
            logger.info(msg);

        } catch (Exception e) {
            logger.error("handle push message error!", e);
        }

    }
}
