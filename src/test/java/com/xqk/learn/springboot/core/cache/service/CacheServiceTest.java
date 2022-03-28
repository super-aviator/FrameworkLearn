package com.xqk.learn.springboot.core.cache.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class CacheServiceTest {
    @Autowired
    private CacheService cacheService;

    @Test
    void cacheMethod() {
        //执行查询
        for (long i = 0; i < 2; i++) {
            cacheService.cacheMethod(null);
        }
        //不执行查询
        for (long i = 0; i < 2; i++) {
            cacheService.cacheMethod(i);
        }
    }

    @Test
    void cacheMethodWithoutArgs() {
        //执行查询
        for (long i = 0; i < 2; i++) {
            cacheService.cacheMethodWithoutArgs();
        }
        //不执行查询
        for (long i = 0; i < 2; i++) {
            cacheService.cacheMethodWithoutArgs();
        }
    }

    @Test
    void cacheMethodWithoutArgsReturnNull() {
        //执行查询
        for (long i = 0; i < 2; i++) {
            cacheService.cacheMethodWithoutArgsReturnNull();
        }
        //不执行查询
        for (long i = 0; i < 2; i++) {
            cacheService.cacheMethodWithoutArgsReturnNull();
        }
    }

    @Test
    void multiCacheNames() {
        cacheService.multiCacheNames(1);
    }

    @Test
    void cacheCondition() {
        cacheService.cacheCondition("123");
        cacheService.cacheCondition("12345");
        cacheService.cacheCondition("123456");
        cacheService.cacheCondition("123456");
        cacheService.cacheCondition("1234567");
        cacheService.updateCacheCondition("1234567");
    }

    @Test
    void clean() {
        // cacheService.clean("1234567");
        cacheService.spel();
    }
}