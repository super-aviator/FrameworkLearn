package com.springboot.learn.mvc.config;

import com.springboot.learn.mvc.bean.MyHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 通过继承WebMvcConfigurationSupport类，并重写addInterceptors方法注册自定义的拦截器
 * 通过注册拦截器返回的InterceptorRegistration来设置该拦截器的拦截路径和排除路径
 *
 * @author 熊乾坤
 */
@Configuration
public class HandlerInterceptorRegister extends WebMvcConfigurationSupport {

    private final MyHandlerInterceptor myHandlerInterceptor;

    @Autowired
    public HandlerInterceptorRegister(MyHandlerInterceptor myHandlerInterceptor) {
        this.myHandlerInterceptor = myHandlerInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(myHandlerInterceptor);
        //新增拦截路径
        registration.addPathPatterns("/time/**");
        //新增非拦截路径
        registration.excludePathPatterns("/user/**");
    }
}
