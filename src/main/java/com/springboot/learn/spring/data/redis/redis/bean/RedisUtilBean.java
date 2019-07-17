package com.springboot.learn.spring.data.redis.redis.bean;

import com.springboot.learn.spring.data.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Aviator
 */
@Component
@Profile("redis")
public class RedisUtilBean {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String, User> redisTemplate;

    /**
     * 需要添加commons-pool2依赖
     */
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    public void test() {
    }
}
