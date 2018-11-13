package com.btc123.app.redis;


import java.util.Map;
import java.util.Set;

public interface RedisCacheI {
	/**
	 * 删除多个key
	 *
	 * @param keys
	 */
	public void remove(final String... keys);

	/**
	 * 删除一个key
	 *
	 * @param key
	 */
	public void remove(final String key);

	/**
	 * 得到对应的value
	 *
	 * @param key
	 * @return
	 */
	public Object get(final String key);

	/**
	 * set一个对象
	 *
	 * @param key
	 * @param value
	 * @param expireTime
	 */
	public void set(final String key, Object value, Integer expireTime);

	/**
	 * set时间
	 *
	 * @param key
	 * @param expireTime
	 */
	public void setExpire(final String key, Integer expireTime);

	/**
	 * 判断key是否存在
	 *
	 * @param key
	 * @return
	 */
	public boolean exists(final String key);

	/**
	 * 得到key时间
	 *
	 * @param key
	 * @return
	 */
	public Long ttl(final String key);

	/**
	 * 配置多个key
	 *
	 * @param pattern
	 * @return
	 */
	public Set<String> matchKeys(String pattern);

	/**
	 * 存一个map
	 * @param expireTime
	 * @param maps
	 */
	public void setHmset(Map<String, Object> maps, Integer expireTime);

	/**
	 * get一个map
	 * @param keys
	 * @return
	 */
	public Object getHmget(String keys);

	/**
	 *
	 * @param key
	 */
	public Integer increment(String key, Integer expireTime, Integer value);

	/**
	 *
	 * @param key
	 * @return
	 */
	Integer getIntValue(String key);

	/**
	 *
	 * @param key
	 * @return
	 */
	Integer getIncrAddAndGet(String key, Integer value);

	/**
	 * 取所有的keys
	 * @param key 获取的key
	 */
	Set<String> keys(String key);
}


