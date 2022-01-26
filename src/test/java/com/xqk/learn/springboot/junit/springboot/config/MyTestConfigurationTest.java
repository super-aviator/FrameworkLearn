package com.xqk.learn.springboot.junit.springboot.config;

import com.xqk.learn.springboot.common.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TestConfigurationTest
 *
 * @author xiongqiankun
 * @since 2022/1/25 19:41
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MyTestConfigurationTest {
    @Autowired
    private Foo foo;

    @Test
    public void test() {
        Assertions.assertEquals("foo999", foo.getName());
    }
}