package com.vivo.uhost.web.mq;

import com.vivo.uhost.comm.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import java.io.IOException;

/**
 * @Author: DongJiaJin
 * @Description:web server消费从push server发过来的消息,然后转发给浏览器
 * @Date: Created in 22:35 2018/5/6
 * @Modified By:
 */
public class AutoRefreshConsumer  implements MessageListener {
    private static Logger logger = LoggerFactory.getLogger(AutoRefreshConsumer.class);

    @Override
    public void onMessage(Message message, byte[] bytes) {
        if(message == null){
            return;
        }
        try {
            String msg = message.toString();
            logger.info("received mesage from Topic-[server]: {}", msg);
            try {
                new WebSocketServer().sendToAllMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("handle push message error!", e);
        }
    }
}
