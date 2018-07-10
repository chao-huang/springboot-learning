package com.sun.config;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.sun.util.RedisObjectSerializer;
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
	@Value("${springext.cache.redis.topic}")
	String topicName;
	/*@Resource
	private JedisConnectionFactory jedisConnectionFactory ;*/
	/*@Bean
	public KeyGenerator simpleKeyGenerator() {
	    return (target, method, params) -> {
	      StringBuilder sb = new StringBuilder();
	      sb.append(target.getClass().getName());
	      sb.append(method.getName());
	      for (Object obj : params) {
	        sb.append(obj.toString());
	      }
	      return sb.toString();
	    };
	  }*/
	 /**
     * 生成key的策略
     * @return
     */
    @Bean
    public KeyGenerator KeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());//取得类名
                sb.append(method.getName());//取得方法名
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template; 
	}
	@Bean
	public TwoLevelCacheManager cacheManager(RedisTemplate<String, Object> redisTemplate){
		RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisTemplate.getConnectionFactory());
		SerializationPair pair = SerializationPair.fromSerializer(new JdkSerializationRedisSerializer(this.getClass().getClassLoader())); 
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
		TwoLevelCacheManager cacheManager = new TwoLevelCacheManager(redisTemplate,writer,config); 
		return cacheManager;
	}
	@Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(topicName));
        return container;
    }
	 @Bean
	 public MessageListenerAdapter listenerAdapter(TwoLevelCacheManager cacheManager){
		 return new MessageListenerAdapter(new MessageListener() {
			@Override
			public void onMessage(Message message, byte[] pattern) {
				byte[] bs = message.getChannel();
				try {
					//sub一个消息，通知缓存管理器，这里的type就是Cache的名字
					String type = new String(bs,"UTF-8");
					cacheManager.receiver(type);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			 
		 });
	 }
}
