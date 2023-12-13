package com.xqk.learn.framework.data.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试String类型的数据结构的用法
 *
 * @author 熊乾坤
 * @since 2020-05-07 15:14
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StringTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void append() {
        String key = "string-key";
        Integer len = redisTemplate.opsForValue().append(key, "abc");
        log.info(len.toString());
        Assert.assertEquals("abc", redisTemplate.opsForValue().get(key));
    }
}
