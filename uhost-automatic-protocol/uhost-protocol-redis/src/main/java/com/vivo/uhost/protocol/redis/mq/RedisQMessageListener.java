package com.vivo.uhost.protocol.redis.mq;

public interface RedisQMessageListener {

    void onMessage(String message);
}
