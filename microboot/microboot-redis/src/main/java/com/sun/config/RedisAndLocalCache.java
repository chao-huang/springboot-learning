package com.sun.config;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;

public class RedisAndLocalCache implements Cache {
	Logger logger = LoggerFactory.getLogger(RedisAndLocalCache.class);
	ConcurrentHashMap<Object, Object> local = new ConcurrentHashMap<Object, Object>();
	RedisCache redisCache;
	TwoLevelCacheManager cacheManager;
	
	public RedisAndLocalCache(TwoLevelCacheManager cacheManager,RedisCache redisCache){
		this.cacheManager = cacheManager;
		this.redisCache = redisCache;
	}
	@Override
	public void clear() {
		this.redisCache.clear();
	}

	@Override
	public void evict(Object key) {
		this.redisCache.evict(key);
		//通知其他节点更新缓存
		clearOtherJVM();
	}
	/**
	 *  变量local代表一个简单的缓存实现，使用了ConcurrentHashMap,其get方法有如下逻辑实现
	 *  通过key从本地取出ValueWrapper，如果存在，则直接返回，不存在，则调用RedisCache取得缓存项；
	 *  如果缓存项为空，则说明暂时没有此项，直接返回null，等待@Cacheable调用业务层方法获取缓存项
	 */
	@Override
	public ValueWrapper get(Object key) {
		// 一级缓存
		ValueWrapper  wrapper = (ValueWrapper) local.get(key);
		if(wrapper!=null){
			logger.info("执行一级缓存");
			return wrapper;
		}else{
			// 二级缓存
			wrapper = this.redisCache.get(key);
			logger.info("执行二级缓存");
			if(wrapper!=null){
				local.put(key, wrapper);
			}
		}
		return wrapper;
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		// TODO Auto-generated method stub
		return this.redisCache.get(key, type);
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		// TODO Auto-generated method stub
		return this.redisCache.get(key, valueLoader);
	}

	@Override
	public String getName() {
		return this.redisCache.getName();
	}

	@Override
	public Object getNativeCache() {
		return this.redisCache.getNativeCache();
	}
	/**
	 * 先调用redisCache的方法，更新二级缓存，调用clearOtherJVM方法，通知其他节点更新
	 * 其他的节点包括TwoLevelCacheManager收到消息后，会调用reciver方法从而实现一级缓存
	 */
	@Override
	public void put(Object key, Object value) {		
		logger.info(""+value.getClass().getClassLoader());
		this.redisCache.put(key, value);
		//通知其他节点更新缓存
		clearOtherJVM();
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		ValueWrapper wrapper = this.redisCache.putIfAbsent(key, value);
		//通知其他节点更新缓存
		clearOtherJVM();
		return wrapper;
	}
	
	protected void clearOtherJVM(){
		this.cacheManager.publishMessage(redisCache.getName());
	}
	//提供CacheManager清空一级缓存
	public void clearLocal(){
		this.local.clear();
	}
}
