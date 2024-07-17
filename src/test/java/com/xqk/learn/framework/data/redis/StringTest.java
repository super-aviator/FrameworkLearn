package com.xqk.learn.framework.data.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 测试String类型的数据结构的用法
 *
 * @author 熊乾坤
 * @since 2020-05-07 15:14
 */
@Slf4j
@SpringBootTest
public class StringTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void append() {
        String key = "string-key";
        Integer len = redisTemplate.opsForValue().append(key, "abc");
        log.info(len.toString());
        Assertions.assertEquals("abc", redisTemplate.opsForValue().get(key));
    }
}
