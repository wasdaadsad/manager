/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.server.mq;

import com.bbkmobile.iqoo.common.util.JsonUtil;
import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.thread.ThreadDistribution;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.message.model.DeviceStateInfo;
import com.vivo.uhost.protocol.core.model.mq.TaskStateNotice;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
import com.vivo.uhost.service.IDeviceTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author huangxiaoqun  2018/3/26 20:09
 * @version 1.0
 * @description
 */
public class TopicDeviceTaskStateConsumer implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(TopicDeviceLoginStateConsumer.class);

    @Autowired
    private IDeviceTaskService deviceTaskService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        if (message == null || null == message.getBody()) {
            return;
        }

        try {


            final TaskStateNotice taskStateNotice = JsonUtil.json2Bean(message.toString(), TaskStateNotice.class);

            ThreadDistribution.getInstance().submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    //更改设备测试状态 设置参数
                    logger.info("change Task State");
                    Map<String, Integer> resultMap = new HashMap<>();
                    for (DeviceStateInfo deviceStateInfo : taskStateNotice.getResult()) {
                        resultMap.put(deviceStateInfo.getEmecid(), deviceStateInfo.getState());
                    }
                    deviceTaskService.changeTaskState(taskStateNotice.getMsgId(), taskStateNotice.getToken(), resultMap);
                    //通知浏览器刷新任务状态列表和流程列表
                    Thread.sleep(3000);
                    RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
                    redisQProducer.sendPageRefresh(Constants.REFRESH_TASK_STATE);//这里和前端对应，消息体中包含taskState
                    return null;
                }
            });


        } catch (Exception ex) {

        }


        String msg = message.toString();
        logger.info("received mesage from Topic-[push]: {}", msg);
    }
}
