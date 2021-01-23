package com.xqk.learn.springboot.mvc.controller;

import com.xqk.learn.springboot.cloud.openfeign.client.BaseClient;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 熊乾坤
 * @since 2020-12-27 22:15
 */
@Slf4j
@SpringBootTest
@Profile("default")
@RunWith(SpringRunner.class)
public class BaseControllerTest extends TestCase {
    @Autowired
    BaseClient baseClient;

    @Test
    public void testGetMessage() {
        log.info(baseClient.getMessage());
    }

    @Test
    public void testGetMessageAfterSleepMilliSeconds() {
        log.info(baseClient.getMessageAfterSleepMilliSeconds(1000L));
    }
}