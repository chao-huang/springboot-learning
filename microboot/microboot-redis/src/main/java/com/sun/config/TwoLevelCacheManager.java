package com.sun.config;


import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.sun.aop.ApplicationAop;

public class TwoLevelCacheManager extends RedisCacheManager {
	
	
	public TwoLevelCacheManager(RedisTemplate<String, Object> redisTemplate,RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
		super(cacheWriter, defaultCacheConfiguration);
		this.redisTemplate=redisTemplate;
	}
	Logger logger = LoggerFactory.getLogger(TwoLevelCacheManager.class);
	@Value("${springext.cache.redis.topic}")
	String topicName;
	@Resource
	RedisTemplate<String, Object> redisTemplate;	
	
	@Override
	protected Cache decorateCache(Cache cache){
		return new RedisAndLocalCache(this,(RedisCache)cache);
	}
	public void publishMessage(String cacheName){
		this.redisTemplate.convertAndSend(topicName, cacheName);
	}
	//接受一个消息清空本地缓存
	public void receiver(String name){
		RedisAndLocalCache cache = ((RedisAndLocalCache)this.getCache(name));
		if(cache!=null){
			logger.info("通知一级缓存，进行缓存更新");
			cache.clearLocal();
		}
	}
}
