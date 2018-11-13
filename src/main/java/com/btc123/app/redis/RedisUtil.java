package com.btc123.app.redis;


import com.btc123.app.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.Map;
import java.util.Set;

/**
 * @author shining
 */
@Component
public class RedisUtil {

	private static RedisCacheI redisCacheI;
	@Autowired
	public RedisUtil(RedisCacheI redisCacheI){
		RedisUtil.redisCacheI =  redisCacheI;
	}
	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return redisCacheI.get(key);
	}

	/**
	 * 设置时间
	 * 
	 * @param key
	 * @return
	 */
	public static void setExpire(String key, Integer seconds) {
		redisCacheI.setExpire(key, seconds);
	}

	/**
	 * 是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {
		return redisCacheI.exists(key);
	}

	/**
	 * 设置值
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, Object value, Integer expire) {
		redisCacheI.set(key, value, expire);
	}

	public static void del(String... key) {
		redisCacheI.remove(key);
	}

	public static void del(String key) {
		redisCacheI.remove(key);
	}

	public static long ttl(String key) {
		return redisCacheI.ttl(key);
	}

	public static Set<String> matchKeys(String key) {
		return redisCacheI.matchKeys(key);
	}

	public static void setHmset(Map<String,Object> maps,Integer expire){
		redisCacheI.setHmset(maps,expire);
	}

	public static Object getHmset(String key){
		return redisCacheI.getHmget(key);
	}

	public static void deleteAll(String key){
		Set<String> keys = keys(key);
		if (!CollectionUtils.isEmpty(keys)){
			for (String keySet : keys) {
				del(keySet);
			}
		}
	}

	public static void set(String key, Object value){
		redisCacheI.set(key, value, (int) Constants.DEFAULT_EXPIRATION);
	}


	public static Integer setIncrement(String key,Integer value,Integer expireTime){
		return redisCacheI.increment(key,expireTime,value);
	}
	public static Integer getIncrAddAndGet(String key,Integer value){
		return redisCacheI.getIncrAddAndGet(key,value);
	}
	public static Integer getIntValue(String key){
		return  redisCacheI.getIntValue(key);
	}
	public static Set<String> keys(String key){
		return  redisCacheI.keys(key);
	}
}
