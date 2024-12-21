package com.xqk.lean.framework.springboot.client;

import com.xqk.lean.framework.springboot.cloud.openfeign.client.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

/**
 * @author 熊乾坤
 * @since 2021-02-21 17:07
 */
@Slf4j
@SpringBootTest
@Profile("default")
public class BaseClientTest {
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