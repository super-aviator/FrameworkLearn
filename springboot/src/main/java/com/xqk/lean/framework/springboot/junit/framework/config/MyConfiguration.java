package com.xqk.lean.framework.springboot.junit.framework.config;

import com.xqk.lean.framework.springboot.common.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyConfiguration
 * 注解@TestConfiguration中的bean能够覆盖@Configuration的bean
 *
 * @author xiongqiankun
 * @since 2022/1/25 19:47
 */
@Configuration
public class MyConfiguration {
    @Bean
    public Foo foo() {
        return new Foo("foo999");
    }
}
