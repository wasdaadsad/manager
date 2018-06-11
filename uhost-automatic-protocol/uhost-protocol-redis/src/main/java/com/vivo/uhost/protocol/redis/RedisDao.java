package com.vivo.uhost.protocol.redis;


import org.springframework.data.redis.core.Cursor;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisDao {

	void del(String key);

	boolean hasKey(String key);
	
	void incr(String key);
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param expired TimeUnit.SECONDS
	 */
	void set(String key, String value, long expired);
	
	void set(String key, String value);
	
	String get(String key);
	
	Set<String> getKeys(String pattern);

	Long getIncr(String key);
	
	void setAdd(String key, String value);
	
	boolean isSetMember(String key, String value);
	
	long setRemove(String key, String value);
	
	Long setSize(String key);
	
	void hashAdd(String key, String hashKey, String value);

	void hashIncr(String key, String hashKey, long value);

	void hashRemove(String key, String hashKey);
	
	boolean isHashKey(String key, String hashKey);
	
	String getHashValue(String key, String hashKey);

	Map<String, String> entries(String key);

	Cursor<Map.Entry<String, String>> hashScan(String key, long count);

	Long hashSize(String key);
	
	Set<String> getZSetPaged(String key, long start, long end);
	
	boolean zsetAdd(String key, String value, double score);
	
	Long zsetSize(String key);
	
	void leftPush(String key, String value);
	
	String rightPop(String key);

	String rightPop(String key, long timeout, TimeUnit unit);

	String rightPopLeftPush(String srcKey, String dstKey);

	String rightPopLeftPush(String srcKey, String dstKey, long timeout, TimeUnit unit);

	Long listSize(String key);

	void sendMessage(String channel, String msg);
}
