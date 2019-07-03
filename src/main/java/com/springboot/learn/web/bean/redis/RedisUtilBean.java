package com.springboot.learn.web.bean.redis;

import com.springboot.learn.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Aviator
 */
@Component
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

}
