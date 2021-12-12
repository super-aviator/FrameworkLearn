package com.xqk.learn.springboot.debezium.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * DebeizumConfig
 *
 * @author xiongqiankun
 * @since 2021/12/12 12:39
 */
@Data
@Configuration
@EnableConfigurationProperties(DebeziumConfig.class)
@ConfigurationProperties(prefix = "debezium")
public class DebeziumConfig {
    private String enabled;
    private String topic;
    private Properties properties;
}
