package com.xqk.learn.springboot.mybatis.config;

import com.xqk.learn.springboot.mybatis.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisConfiguration
 *
 * @author xiongqiankun
 * @since 2022/5/15 16:23
 */
@Configuration
public class MybatisConfiguration {
        @Bean
        MyInterceptor sqlExplainInterceptor() {
            return new MyInterceptor();
        }
}
