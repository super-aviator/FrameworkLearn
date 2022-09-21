package com.xqk.learn.framework.cloud.openfeign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * SpringOpenFeignConfiguration
 * <p>
 * 开启Feign，并指定默认配置（也可以在配置文件中指定，配置文件的优先级更高）
 *
 * @author xiongqiankun
 * @since 2022/1/26 11:07
 */
@EnableFeignClients(basePackages = "com.xqk.learn.framework", defaultConfiguration = BaseClientConfiguration.class)
@Configuration
public class SpringOpenFeignConfiguration {
}
