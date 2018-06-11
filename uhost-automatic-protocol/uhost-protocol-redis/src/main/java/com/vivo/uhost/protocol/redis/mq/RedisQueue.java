package com.vivo.uhost.protocol.redis.mq;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-2
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class RedisQueue {

    private String queueName;

    private RedisQMessageListener messageListener;

    private int concurrentSize = 1;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public RedisQMessageListener getMessageListener() {
        return messageListener;
    }

    public void setMessageListener(RedisQMessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public int getConcurrentSize() {
        return concurrentSize;
    }

    public void setConcurrentSize(int concurrentSize) {
        this.concurrentSize = concurrentSize;
    }
}
