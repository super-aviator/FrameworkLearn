package com.springboot.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LearnApplicationTests {

    @Autowired
    Environment environment;


    @Test
    public void contextLoads() {
        log.info(Arrays.toString(environment.getDefaultProfiles()));
        log.info(environment.getProperty("spring.datasource.url"));
    }
}
