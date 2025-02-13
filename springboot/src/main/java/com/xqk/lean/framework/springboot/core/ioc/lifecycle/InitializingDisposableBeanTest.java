package com.xqk.lean.framework.springboot.core.ioc.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;
import org.springframework.web.context.ServletContextAware;

/**
 * @author 熊乾坤
 */
@Slf4j
public class InitializingDisposableBeanTest implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware,
        EnvironmentAware, EmbeddedValueResolverAware, ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware, ServletContextAware,
        InitializingBean, DisposableBean {


    public InitializingDisposableBeanTest(){
        log.info("InitializingDisposableBeanTest#Constructor");
    }

    @Autowired
    public void setMyBeanPostProcessor(MyBeanPostProcessor myBeanPostProcessor) {
        log.info("InitializingDisposableBeanTest#setProperties");
    }


    @Override
    public void setBeanName(String name) {
        log.info("BeanNameAware-setBeanName({})", name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.info("BeanClassLoadAware-setBeanClassLoader({})", classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("BeanFactoryAware-setBeanFactory({})", beanFactory);
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.info("Environment-setEnvironment({})", environment);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        log.info("EmbeddedValueResolverAware-setEmbeddedValueResolver({})", resolver);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        log.info("ResourceLoaderAware-setResourceLoader({})", resourceLoader);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        log.info("ApplicationEventPublisherAware-setApplicationEventPublisher({})", applicationEventPublisher);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        log.info("MessageSourceAware-setMessageSource({})", messageSource);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("ApplicationContextAware-setApplicationContext({})", applicationContext);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        log.info("ServletContextAware-setServletContext({})", servletContext);
    }

    @Override
    public void afterPropertiesSet() {
        log.info("InitializingBean-afterPropertiesSet()");
    }

    @PostConstruct
    public void postConstruct() {
        log.info("@PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("@PreDestroy");
    }

    @Override
    public void destroy() {
        log.info("DisposableBean-destroy()");
    }

    /**
     * PostConstruct注解的方法会在容器加载该bean时被调用，首先调用构造器初始化bean，然后进行依赖注入，最后依次调用PostConstruct注解的方法
     * PreDestroy注解的方法会在容器销毁时被调用
     * <p>
     * 使用AnnotationConfigApplicationContext类，可以根据传入的@Configuration注解的类，来启动容器，非常的神奇
     * 日期 2019/10/10 15:40
     *
     * @author 熊乾坤
     */
        public static void main(String[] args) {
            //可以在AnnotationConfigApplicationContext的构造方法中传入配置的Class
            AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                    "com.xqk.learn.framework.springboot.core.ioc.lifecycle");
            InitializingDisposableBeanTest initializingDisposableBeanTest = applicationContext.getBean(InitializingDisposableBeanTest.class);
            applicationContext.registerShutdownHook();

            // log.info("******************************************************************");
            // annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
            // //如果需要包含多个配置类，可以使用register的方式来进行配置
            // annotationConfigApplicationContext.register(MyBeanPostProcessor.class);
            // annotationConfigApplicationContext.register(InitializingDisposableBeanTest.class);
            // //注意！！！要显示的调用refresh来引用注册的配置类（在refresh()方法中它们所有的@Bean方法都会被处理，在容器中注册成为bean）
            // annotationConfigApplicationContext.refresh();
            // initializingDisposableBeanTest = annotationConfigApplicationContext.getBean(InitializingDisposableBeanTest.class);
            // annotationConfigApplicationContext.close();
        }

    @Configuration
    public static class MyConfiguration {
        @Bean
        public static InitializingDisposableBeanTest getInitializingDisposableBeanTest() {
            return new InitializingDisposableBeanTest();
        }
    }
}