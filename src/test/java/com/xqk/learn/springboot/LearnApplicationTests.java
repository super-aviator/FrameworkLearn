package com.xqk.learn.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * The type Learn application tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LearnApplicationTests {

    /**
     * The Environment.
     */
    @Autowired
    Environment environment;


    /**
     * Context loads.
     */
    @Test
    public void contextLoads() {
        log.info(Arrays.toString(environment.getDefaultProfiles()));
        log.info(environment.getProperty("spring.datasource.url"));
    }

    /**
     * Test.
     */
    @Test
    public void test() {
        System.out.println((String) null);
    }
}
