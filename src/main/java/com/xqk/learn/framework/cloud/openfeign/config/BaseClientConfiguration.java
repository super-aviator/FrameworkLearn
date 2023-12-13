package com.xqk.learn.framework.cloud.openfeign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author 熊乾坤
 * @since 2020-11-22 21:58
 */
public class BaseClientConfiguration {
    /**
     * 指定Feign日志的级别
     */
    @Bean
    Logger.Level getLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
