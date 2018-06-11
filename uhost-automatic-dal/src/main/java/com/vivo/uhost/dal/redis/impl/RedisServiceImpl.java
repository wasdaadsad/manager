package com.vivo.uhost.dal.redis.impl;

import com.vivo.uhost.dal.redis.IRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.util.SafeEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("redisService")
public class RedisServiceImpl implements IRedisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private JedisPool jedisPool;

    /**
     * ValueOperations：简单K-V操作 SetOperations：set类型数据操作 ZSetOperations：zset类型数据操作
     * HashOperations：针对map类型的数据操作 ListOperations：针对list类型的数据操作
     */

    @Override
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception ex) {
            LOGGER.error("del mess failed", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void hdel(String key, String hashKey) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hdel(key, hashKey);
        } catch (Exception ex) {
            LOGGER.error("hdel mess failed", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public boolean hasKey(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception ex) {
            LOGGER.error("hasKey failed", ex);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public boolean hexists(String key, String filed) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hexists(key, filed);
        } catch (Exception ex) {
            LOGGER.error("hexists failed", ex);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public void lpush(String key, String... val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(key, val);
        } catch (Exception ex) {
            LOGGER.error("lpush failed", ex);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> lrange(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lrange(key, 0, -1);
        } catch (Exception ex) {
            LOGGER.error("lrange failed", ex);
            return new ArrayList<>();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void publishMsg(String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.publish(channel, message);
        } catch (Exception e) {
            LOGGER.error("lrange failed", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void subscribeMsg(JedisPubSub jedisPubSub, String... channels) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.subscribe(jedisPubSub, channels);
            LOGGER.info("subscribeMsg {} = {}", jedisPubSub, channels);
        } catch (Exception e) {
            LOGGER.error("subscribeMsg {} = {}", jedisPubSub, channels, e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public void incr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.incr(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.decr(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public byte[] hgetBytes(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(SafeEncoder.encode(key), SafeEncoder.encode(field));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hset(String key, String field, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hset(SafeEncoder.encode(key), SafeEncoder.encode(field), value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public void incr(String key, int value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.incrBy(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void set(String key, String value, long expired) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            jedis.expire(key, new Long(expired).intValue());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 游标遍历结果集
     *
     * @param keyPerfix
     * @return
     */
    @Override
    public List<String> scan(String keyPerfix) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            ScanParams params = new ScanParams();
            params.match(keyPerfix);
            ScanResult<String> scanResult = jedis.scan("0", params);
            List<String> keys = scanResult.getResult();
            String nextCursor = scanResult.getStringCursor();
            List<String> keyList = new ArrayList<>();
            while (true) {
                keyList.addAll(keys);
                if (nextCursor.equals("0")) {
                    break;
                }
                scanResult = jedis.scan(nextCursor, params);
                nextCursor = scanResult.getStringCursor();
                keys = scanResult.getResult();
            }
            return keyList;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 游标hash遍历结果集
     *
     * @param key 遍历hash集合
     * @return  结果
     */
    @Override
    public List<Map.Entry<String, String>> hscan(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(key, "0");
            List<Map.Entry<String, String>> entries = scanResult.getResult();
            String nextCursor = scanResult.getStringCursor();
            List<Map.Entry<String, String>> keyList = new ArrayList<>();
            while (true) {
                keyList.addAll(entries);
                if (nextCursor.equals("0")) {
                    break;
                }
                scanResult = jedis.hscan(key, nextCursor);
                nextCursor = scanResult.getStringCursor();
                entries = scanResult.getResult();
            }
            return keyList;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public void hashSet(String key, Map<String, String> map) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hmset(key, map);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void hashSet(String key, String hashKey, String hashValue) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, hashKey, hashValue);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Map<String, String> entries(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hgetAll(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String hget(String key, String hashKey) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key, hashKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public void expireSecond(String key, long cacheExpiredTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.expire(key, new Long(cacheExpiredTime).intValue());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
