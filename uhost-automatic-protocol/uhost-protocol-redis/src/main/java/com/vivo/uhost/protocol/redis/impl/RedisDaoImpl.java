package com.vivo.uhost.protocol.redis.impl;

import com.vivo.uhost.protocol.redis.RedisDao;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository("redisDao")
public class RedisDaoImpl implements RedisDao {

	/**
	 * ValueOperations：简单K-V操作 SetOperations：set类型数据操作 ZSetOperations：zset类型数据操作
	 * HashOperations：针对map类型的数据操作 ListOperations：针对list类型的数据操作
	 */

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valOps;

	@Resource(name = "redisTemplate")
	private SetOperations<String, String> setOps;

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, String> hashOps;

	@Resource(name="redisTemplate")
    private ZSetOperations<String, String> zsetOps;
	
	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public Set<String> getZSetPaged(String key, long start, long end){
		return zsetOps.range(key, start, end);
	}

	@Override
	public boolean zsetAdd(String key, String value, double score){
		return zsetOps.add(key, value, score);
	}

	@Override
	public Long zsetSize(String key){
		return zsetOps.size(key);
	}
	
	@Override
	public void del(String key) {
		valOps.getOperations().delete(key);
	}

	@Override
	public boolean hasKey(String key) {
		return valOps.getOperations().hasKey(key);
	}

	@Override
	public void incr(String key) {
		valOps.increment(key, 1L);
	}

	@Override
	public void set(String key, String value, long expired) {
		valOps.set(key, value, expired, TimeUnit.SECONDS);
	}
	
	@Override
	public void set(String key, String value) {
		valOps.set(key, value);
	}

	@Override
	public String get(String key) {
		return valOps.get(key);
	}

	@Override
	public Set<String> getKeys(String pattern) {
		return valOps.getOperations().keys(pattern);
	}

	@Override
	public Long getIncr(String key) {
		Object value = valOps.get(key);
		if(value == null){
			return 0L;
		}
		return Long.valueOf(valOps.get(key).toString());
	}

	@Override
	public void setAdd(String key, String value) {
		setOps.add(key, value);
	}

	@Override
	public boolean isSetMember(String key, String value) {
		return setOps.isMember(key, value);
	}
	
	@Override
	public long setRemove(String key, String value){
		return setOps.remove(key, value);
	}

	@Override
	public Long setSize(String key) {
		return setOps.size(key);
	}

	@Override
	public void hashAdd(String key, String hashKey, String value) {
		hashOps.put(key, hashKey, value);
	}

	@Override
	public void hashIncr(String key, String hashKey, long value) {
		hashOps.increment(key, hashKey, value);
	}

	@Override
	public void hashRemove(String key, String hashKey){
		hashOps.delete(key, hashKey);
	}

	@Override
	public boolean isHashKey(String key, String hashKey){
		return hashOps.hasKey(key, hashKey);
	}

	@Override
	public String getHashValue(String key, String hashKey){
		return hashOps.get(key, hashKey);
	}

	@Override
	public Map<String, String> entries(String key){
		return hashOps.entries(key);
	}

	@Override
	public Cursor<Map.Entry<String, String>> hashScan(String key, long count){
		return hashOps.scan(key, ScanOptions.scanOptions().count(count).build());
	}

	@Override
	public Long hashSize(String key) {
		return hashOps.size(key);
	}

	@Override
	public void leftPush(String key, String value) {
		listOps.leftPush(key, value);
	}

	@Override
	public String rightPop(String key) {
		return listOps.rightPop(key);
	}

	@Override
	public String rightPop(String key, long timeout, TimeUnit unit) {
		return listOps.rightPop(key, timeout, unit);
	}

	@Override
	public String rightPopLeftPush(String srcKey, String dstKey) {
		return listOps.rightPopAndLeftPush(srcKey, dstKey);
	}

	@Override
	public String rightPopLeftPush(String srcKey, String dstKey, long timeout, TimeUnit unit) {
		return listOps.rightPopAndLeftPush(srcKey, dstKey, timeout, unit);
	}
	
	@Override
	public Long listSize(String key){
		return listOps.size(key);
	}

	@Override
	public void sendMessage(String channel, String msg) {
		redisTemplate.convertAndSend(channel, msg);
	}
}
