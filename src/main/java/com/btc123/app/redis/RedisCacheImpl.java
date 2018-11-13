package com.btc123.app.redis;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author shining
 */
@Component
public class RedisCacheImpl implements RedisCacheI {
    private static final Logger LOG = LoggerFactory
            .getLogger(RedisCacheImpl.class);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    @Override
    public Object get(String key) {
        Object result = null;
        ValueOperations<String, Object> operations = redisTemplate
                .opsForValue();
        result = operations.get(key);
        return result;
    }

    @Override
    public void set(String key, Object value, Integer expireTime) {
        try {
            ValueOperations<String, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public void setExpire(String key, Integer expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public Long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public Set<String> matchKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public void setHmset(Map<String, Object> obj,Integer expireTime) {
        ValueOperations<String, Object> operations = redisTemplate
                .opsForValue();
        operations.multiSet(obj);
        if (!StringUtils.isEmpty(expireTime)){
            for (Map.Entry<String, Object> entry : obj.entrySet()) {
                setExpire(entry.getKey(),expireTime);
            }
        }
    }

    @Override
    public Object getHmget(String keys) {
        ValueOperations<String, Object> operations = redisTemplate
                .opsForValue();
        return operations.get(keys);
    }

    @Override
    public Integer increment(String key, Integer expireTime,Integer value) {
        RedisAtomicInteger counter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory(),value);
        Integer increment = counter.getAndIncrement();
        //初始设置过期时间
        boolean flag = (null == increment || increment.intValue() == 0) && (expireTime != null && expireTime > 0);
        if (flag) {
            counter.expire(expireTime, TimeUnit.SECONDS);
        }
        return increment;
    }
    @Override
    public Integer getIncrAddAndGet(String key,Integer value){
        RedisAtomicInteger counter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
        Integer increment = value == null?counter.getAndIncrement():counter.addAndGet(value);
        return increment;
    }
    @Override
    public Integer getIntValue(String key) {
        return redisTemplate.execute(new RedisCallback<Integer>() {
            @Override
            public Integer doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] rowkey = serializer.serialize(key);
                byte[] rowval = connection.get(rowkey);
                String val = serializer.deserialize(rowval);
                if (val != null){
                    return Integer.valueOf(val);
                }
                return null;
            }
        });
    }

    @Override
    public Set<String>  keys(String key) {
        return redisTemplate.keys(key);
    }
}
