package com.sun.redisservice;

import java.util.Map;
import java.util.Set;

public interface IRedisService {
	 /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys);

    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(final String pattern);

    /**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key);

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key);

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key);

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value);

    /**
     * 写入缓存
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime);

    /**
     * 根据Key刷新超时时间
     * @param key
     * @param expireTime
     * @return
     */
    public boolean flushExpireTime(final String key, Long expireTime);


    public Long increase(String key, String field);

    public Long decrease(String key, String field);

    public void deleteField(String key, String field);

    public Set<String> getFields(String key);

    public boolean exists(String key, String field);

    public Object getValueByKeyANdField(String key, String field);

    public Map<String, Object> getEntries(String key);

    public void put(String key, String field, Long value);
}
