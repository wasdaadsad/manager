package com.vivo.uhost.protocol.redis.mq.topic;

import com.vivo.internet.vivocfg.client.VivoConfigManager;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.message.model.DevicePortNameChangeReq;
import com.vivo.uhost.message.model.DeviceStateChangeReq;
import com.vivo.uhost.protocol.core.model.mq.PushNotice;
import com.vivo.uhost.protocol.core.model.mq.TaskStateNotice;
import com.vivo.uhost.protocol.redis.RedisConstans;
import com.vivo.uhost.protocol.redis.RedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

@Service("redisQProducer")
public class RedisQProducer {
    private static final Logger logger = LoggerFactory.getLogger(RedisQProducer.class);

    private static final String REDIS_MQ_DEVICE_STATE = RedisConstans.REDIS_MQ_DEVICE_STATE;

    private static final String REDIS_MQ_UHOST_PORTNAME_STATE = RedisConstans.REDIS_MQ_UHOST_PORTNAME_STATE;

    private static final String REDIS_MQ_TASK_STATE = RedisConstans.REDIS_MQ_TASK_STATE;

    private static final String REDIS_CONSOLE_TASK_CMD = RedisConstans.REDIS_CONSOLE_TASK_CMD;

    private static final String REDIS_TOPIC_PUSH = RedisConstans.REDIS_TOPIC_PUSH;

    private static final String REDIS_TOPIC_SERVER_BREAKDOWN = RedisConstans.REDIS_TOPIC_SERVER_BREAKDOWN;

    private static final String REDIS_TOPIC_SERVER_REFRESH = RedisConstans.REDIS_TOPIC_SERVER_REFRESH;



    @Autowired
    private RedisDao redisDao;

    //发送设备状态    uhost   -> push server
    public void sendDeviceState(DeviceStateChangeReq changeReq) {
        if (changeReq != null) {
            try {
                String message = JsonUtils.toJson(changeReq);
                redisDao.sendMessage(REDIS_MQ_DEVICE_STATE, message);
                logger.info("send message to device state success! message: {}", message);
            } catch (Exception e) {
                logger.error("send message to device state error!", e);
            }
        }
    }

    //发送任务执行状态  uhost -> push serevr
    public void sendTaskState(TaskStateNotice notice) {
        if (notice != null) {
            try {
                String message = JsonUtils.toJson(notice);
                redisDao.sendMessage(REDIS_MQ_TASK_STATE, message);
                logger.info("send message to task state success! message: {}", message);
            } catch (Exception e) {
                logger.error("send message to task state error!", e);
            }
        }
    }


    //发送任务 web - > push Server -> uhost
    public boolean sendPushNotice(PushNotice pushNotice) {
        if (pushNotice == null) {
            logger.error("push notice can not be null!");
            return false;
        }
        try {
            String message = JsonUtils.toJson(pushNotice);
            redisDao.sendMessage(REDIS_TOPIC_PUSH, message);
            logger.info("send push notice task success! message: {}", message);
            return true;
        } catch (Exception e) {
            logger.error("send push notice task failed!", e);
        }
        return false;
    }

    //发送端口状态    uhost   -> push server
    public void sendDeviceStateToPortName (DevicePortNameChangeReq changeReq) {
        if(changeReq != null){
            try {
                String message = JsonUtils.toJson(changeReq);
                redisDao.sendMessage(REDIS_MQ_UHOST_PORTNAME_STATE, message);
                logger.info("send message to uhost port success! message: {}", message);
            } catch (Exception e) {
                logger.error("send message to uhost port error!", e);
            }
        }
    }


    //发送server崩溃状态给web    uhost -> web
    public void sendServerBreakDown (Map params) {
        if(params != null){
            try {
                String message = JsonUtils.toJson(params);
                redisDao.sendMessage(REDIS_TOPIC_SERVER_BREAKDOWN, message);
                logger.info("send message to web success! message: {}", message);
            } catch (Exception e) {
                logger.error("send message to web error!", e);
            }
        }
    }


    //server修改数据库后要通知浏览器刷新页面   server -> web
    public void sendPageRefresh (String msg) {
        if(msg != null){
            try {
                String message = JsonUtils.toJson(msg);
                redisDao.sendMessage(REDIS_TOPIC_SERVER_REFRESH, message);
                logger.info("send message to web success! message: {}", message);
            } catch (Exception e) {
                logger.error("send message to web error!", e);
            }
        }
    }
}
