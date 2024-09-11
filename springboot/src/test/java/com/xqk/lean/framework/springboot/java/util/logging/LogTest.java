package com.xqk.lean.framework.springboot.java.util.logging;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

/**
 * The type Log test.
 */
@Slf4j
@SpringBootTest
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
//        log.info("Log With Class Test", LogTest.class);
//        log.info("Log With Class Test");

        for (int i : IntStream.range(1, 10).toArray()) {
            log.info(String.valueOf(i));
        }
    }
}