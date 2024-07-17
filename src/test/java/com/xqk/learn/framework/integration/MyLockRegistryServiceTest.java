package com.xqk.learn.framework.integration;

import com.xqk.learn.framework.springboot.integration.MyLockRegistryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MyLockRegistryServiceTest {
    @Autowired
    private MyLockRegistryService lockRegistryService;

    @Test
    public void test() throws InterruptedException {
        var lockRegistry = lockRegistryService.createLockRegistry("test", 1000000);
        var lock=lockRegistry.obtain("key");
        try{
            lock.lock();
            log.info("locked!!");
            Thread.sleep(30000);
        }finally{
            lock.unlock();
            log.info("unlocked!!");
        }
        Thread.sleep(30000);
    }
}