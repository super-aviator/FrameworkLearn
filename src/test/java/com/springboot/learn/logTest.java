package com.springboot.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class logTest {
    @Test
    public void testLog() {
        try {
            throw new RuntimeException("***throw e exception");
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
    }
}