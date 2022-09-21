package com.xqk.learn.framework.springboot.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMVC配置
 *
 * @author xiongqiankun
 * @since 2021/9/23 8:35
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * 增加静态资源访问配置
     *
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
