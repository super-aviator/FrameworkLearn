package com.springboot.learn.web.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Aviator
 */
@Component
public class RedisUtilBean implements DisposableBean {
    private final
    StringRedisTemplate stringRedisTemplate;

    /**
     * 需要添加commons-pool2依赖
     */
    private final
    RedisConnectionFactory redisConnectionFactory;

    private final RedisConnection redisConnection;

    @Autowired
    public RedisUtilBean(StringRedisTemplate stringRedisTemplate, RedisConnectionFactory redisConnectionFactory) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisConnectionFactory = redisConnectionFactory;
        redisConnection = redisConnectionFactory.getConnection();
    }

    public String setString(String key, String value) {
        redisConnection.set(key.getBytes(), value.getBytes());
        return new String(Objects.requireNonNull(redisConnection.get(key.getBytes())));
    }

    @Override
    public void destroy() throws Exception {
        redisConnection.close();
    }
}
