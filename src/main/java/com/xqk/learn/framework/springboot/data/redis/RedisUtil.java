package com.xqk.learn.framework.springboot.data.redis;

import com.xqk.learn.framework.springboot.data.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * The type Redis util bean.
 *
 * @author Aviator
 */
@Component
@Profile("redis")
public class RedisUtil {
    /**
     * The String redis template.
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * The Redis template.
     */
    @Autowired
    RedisTemplate<String, User> redisTemplate;

    /**
     * 需要添加commons-pool2依赖
     */
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * Test.
     */
    public void test() {
    }
}
