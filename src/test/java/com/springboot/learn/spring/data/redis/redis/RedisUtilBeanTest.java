package com.springboot.learn.spring.data.redis.redis;

import com.springboot.learn.spring.data.redis.redis.bean.RedisUtilBean;
import com.springboot.learn.spring.data.jpa.common.Gender;
import com.springboot.learn.spring.data.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The type Redis util bean test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class RedisUtilBeanTest {
    /**
     * The Redis util bean.
     */
    @Autowired
    RedisUtilBean redisUtilBean;

    /**
     * 需要添加commons-pool2依赖
     */
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

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
     * Redis test.
     */
    @Test
    public void redisTest(){
        User user = new User();
        user.setName("熊乾坤");
        user.setAddress("湖北宜昌");
        user.setEmail("12321321@qq.com");
        user.setGender(Gender.MALE);
        redisTemplate.opsForValue().set("UserInfo", user);
        User user1 = redisTemplate.opsForValue().get("UserInfo");
        Assert.assertEquals(user, user1);
    }
}
