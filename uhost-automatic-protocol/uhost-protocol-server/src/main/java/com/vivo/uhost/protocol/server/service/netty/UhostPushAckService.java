package com.vivo.uhost.protocol.server.service.netty;

import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.thread.ThreadDistribution;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.message.Message;
import com.vivo.uhost.message.model.PushTaskResp;
import com.vivo.uhost.protocol.core.service.IService;
import com.vivo.uhost.protocol.redis.RedisDao;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
import com.vivo.uhost.protocol.server.service.IDeviceChangeService;
import com.vivo.uhost.protocol.server.utils.RedisKeyUtils;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-6-25
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0 发送任务给客户端之后，客户端给的回应
 */
@Service("uhostPushAckService")
public class UhostPushAckService implements IService {
    private static final Logger logger = LoggerFactory.getLogger(UhostLoginService.class);

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private IDeviceChangeService deviceChangeService;

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
            final PushTaskResp pushTaskResp = JsonUtils.toObject(msg, PushTaskResp.class);
            if (pushTaskResp == null
                    || StringUtil.isBlank(pushTaskResp.getToken())
                    || pushTaskResp.getMsgId() == null
                    || CollectionUtils.isEmpty(pushTaskResp.getResult())) {
                return null;
            }
            String token = pushTaskResp.getToken();
            String tokenKey = RedisKeyUtils.getUhostKey(token);
            Map<String, String> mapEntriy = redisDao.entries(tokenKey);
            if (mapEntriy == null || mapEntriy.isEmpty()) {
                logger.warn("ping failed. token: {}", token);
                return Message.INVALID_TOKEN_MESSAGE;
            }

            ThreadDistribution.getInstance().submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    deviceChangeService.processTaskResult(pushTaskResp);
                    try {
                        //通知浏览器刷新任务状态和流程状态列表
                        Thread.sleep(3000);
                        RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
                        redisQProducer.sendPageRefresh(Constants.REFRESH_TASK_STATE);//这里和前端对应，消息体中包含taskState
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });

            return Message.PING_ACK_MESSAGE;
        } catch (Exception e) {
            logger.error("process push ack msg error!", e);
        }
        return null;
    }
}
