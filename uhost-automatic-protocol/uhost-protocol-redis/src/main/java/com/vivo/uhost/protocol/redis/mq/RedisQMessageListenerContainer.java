package com.vivo.uhost.protocol.redis.mq;

import com.vivo.uhost.protocol.redis.RedisDao;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RedisQMessageListenerContainer implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(RedisQMessageListenerContainer.class);

    private static final long DEFAULT_TIMEOUT = 30;

    @Autowired
    private RedisDao redisDao;

    private Executor taskExecutor;

    private List<RedisQueue> redisQueues;

    private volatile boolean running = false;

    private volatile boolean initialized = false;

    public void start(){
        if(!running && initialized){
            running = true;
            if(CollectionUtils.isNotEmpty(redisQueues)){
                for (RedisQueue redisQueue : redisQueues){
                    if(StringUtil.isBlank(redisQueue.getQueueName()) || redisQueue.getMessageListener() == null){
                        continue;
                    }
                    for(int i = 0; i < redisQueue.getConcurrentSize(); i++){
                        taskExecutor.execute(new MessageListenerTask(redisQueue.getQueueName(), redisQueue.getMessageListener()));
                    }
                    logger.info("Started listening for RedisQ messages, queueName: {}", redisQueue.getQueueName());
                }
            }
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run(){
                    running = false;
                }
            });
        }
    }

    public void stop(){
        running = false;
        logger.debug("Stop RedisQ message listeners");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(taskExecutor == null){
            taskExecutor = Executors.newCachedThreadPool();
        }
        initialized = true;
    }

    public void setTaskExecutor(Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void setRedisQueues(List<RedisQueue> redisQueues) {
        this.redisQueues = redisQueues;
    }

    private class MessageListenerTask implements Runnable{

        public MessageListenerTask(String queueName, RedisQMessageListener messageListener) {
            this.queueName = queueName;
            this.messageListener = messageListener;
        }

        private String queueName;
        private RedisQMessageListener messageListener;

        @Override
        public void run() {
            while (running){
                try {
                    final String message = redisDao.rightPop(queueName, DEFAULT_TIMEOUT, TimeUnit.SECONDS);

                    if(message != null && !message.trim().equals("")){
                        messageListener.onMessage(message);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error("handle MQ message error!", e);
                }
            }
            logger.info("Stop listening for RedisQ messages, queueName: {}", queueName);
        }

    }

}
