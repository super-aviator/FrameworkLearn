package com.xqk.learn.springboot.core.ioc;

import lombok.Data;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * PostConstruct注解的方法会在容器加载该bean时被调用，首先调用构造器初始化bean，然后进行依赖注入，最后依次调用PostConstruct注解的方法
 * PreDestroy注解的方法会在容器销毁时被调用
 * <p>
 * 使用AnnotationConfigApplicationContext类，可以根据传入的@Configuration注解的类，来启动容器，非常的神奇
 * 日期 2019/10/10 15:40
 *
 * @author 熊乾坤
 */
public class BeanPostProcessor {
    @PostConstruct
    public void postConstruct1() {
        //log.info("postConstruct1");
        System.out.println("postConstruct1");
    }

    @PostConstruct
    public void postConstruct2() {
        System.out.println("postConstruct2");

    }

    @PreDestroy
    public void preDestroy1() {
        System.out.println("preDestroy1");
    }

    @PreDestroy
    public void preDestroy2() {
        System.out.println("preDestroy2");
    }

    @Configuration
    @Data
    private static class BeanPostProcessConfig {

        @Bean
        public BeanPostProcessor getBeanPostProcessor() {
            return new BeanPostProcessor();
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanPostProcessConfig.class);
        BeanPostProcessor beanPostProcessor = annotationConfigApplicationContext.getBean(BeanPostProcessor.class);
        System.out.println(beanPostProcessor.toString());
        annotationConfigApplicationContext.close();

        //如果需要包含多个配置类，可以使用register的方式来进行配置
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(BeanPostProcessor.class);
        //注意！！！要显示的调用refresh来引用注册的配置类
        annotationConfigApplicationContext.refresh();
    }
}
