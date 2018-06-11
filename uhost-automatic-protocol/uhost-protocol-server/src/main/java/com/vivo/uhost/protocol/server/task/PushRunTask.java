package com.vivo.uhost.protocol.server.task;

import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.message.Message;
import com.vivo.uhost.message.codec.PackageEncoder;
import com.vivo.uhost.message.model.PushTaskReq;
import com.vivo.uhost.protocol.server.utils.ServerConstants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-3
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class PushRunTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(PushRunTask.class);

    private String token;

    private PushTaskReq pushTaskReq;

    public PushRunTask(String token, PushTaskReq pushTaskReq) {
        this.token = token;
        this.pushTaskReq = pushTaskReq;
    }

    @Override
    public void run() {
        Channel channel = ServerConstants.MAP_TOKEN_CHANNEL.get(token);
        if (channel == null || !channel.isActive()) {
            logger.error("channel is inactive. device's token:{}, taskId:{}, pushType:{}", token, pushTaskReq.getMsgId(), pushTaskReq.getPushType());
            return;
        }

        String pushMsg = JsonUtils.toJson(pushTaskReq);
        Message message = new Message(Constants.CMD_TYPE_SEND, pushMsg);
        byte[] bytes = PackageEncoder.encode(message);
        ByteBuf buf = channel.alloc().buffer(bytes.length);
        buf.writeBytes(bytes);
        channel.writeAndFlush(buf);
        logger.info("push to [{}] success! msg: {}", token, pushMsg);
    }
}
