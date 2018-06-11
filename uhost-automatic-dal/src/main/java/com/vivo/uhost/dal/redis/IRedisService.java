package com.vivo.uhost.dal.redis;

import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Map;

public interface IRedisService {

    void del(String key);

    void hdel(String key, String hashKey);

    boolean hasKey(String key);

    void decr(String key);

    void incr(String key);

    void lpush(String key, String... val);

    List<String> lrange(String key);

    void incr(String key, int value);

    byte[] hgetBytes(String key, String field);

    Long hset(String key, String field, byte[] value);

    void hashSet(String key, Map<String, String> map);

    void hashSet(String key, String hashKey, String hashValue);

    /**
     * 判断hash key是否存在
     *
     * @param key   key
     * @param filed hashkey
     * @return 是否存在
     */
    boolean hexists(String key, String filed);

    /**
     * 获取value
     *
     * @param key     key
     * @param hashKey hashKey
     * @return hash Value
     */
    String hget(String key, String hashKey);

    /**
     * 根据通配符遍历获取key
     *
     * @param key key
     * @return 结果集
     */
    List<String> scan(String key);

    /**
     * 遍历结果集合
     *
     * @param key key
     * @return 结果集
     */
    List<Map.Entry<String, String>> hscan(String key);

    /**
     * 发布消息
     *
     * @param channel
     * @param message
     */
    void publishMsg(String channel, String message);

    /**
     * 订阅消息
     *
     * @param jedisPubSub
     * @param channels
     */
    void subscribeMsg(JedisPubSub jedisPubSub, String... channels);

    /**
     * @param key
     * @param value
     * @param expired TimeUnit.SECONDS
     */
    void set(String key, String value, long expired);

    void set(String key, String value);

    String get(String key);

    Map<String, String> entries(String key);

    void expireSecond(String key, long cacheExpiredTime);


}
