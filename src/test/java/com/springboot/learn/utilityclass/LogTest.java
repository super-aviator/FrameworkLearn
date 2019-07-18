package com.springboot.learn.utilityclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The type Log test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class LogTest {


    /**
     * Log test.
     */
    @Test
    public void logTest() {

        try {
            throw new RuntimeException("***throw e exception");
        } catch (Exception e) {
            //log.info(String msg,Throwable e);
            log.info(e.getMessage(), e);
        }
    }

    /**
     * 日志中，可以使用{}进行占位符的操作,注意，只能应用于字符串，不能应用于对象
     */
    @Test
    public void placeholder() {
        log.info("我在测试{}，你又在划水？", "占位符");
        log.info("我在测试{}，你又在划水？", new Exception());
    }

    /**
     * 两者没有区别
     */
    @Test
    public void LogWithClassTest() {
        log.info("Log With Class Test", LogTest.class);
        log.info("Log With Class Test");
    }
}