package com.xqk.learn.springboot;

import com.xqk.learn.springboot.cloud.openfeign.config.BaseClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.config.client.ConfigClientAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * The type Learn application.
 * 排除定时任务的包
 * 排除redis自动配置类
 * 排除spring security配置类
 * <p>
 * 注解：
 * 1. @SpringBootApplication 相当于@Configuration+@EnableAutoConfiguration+@ComponentScan
 *
 * @author Aviator
 */
//开启Spring定时任务
// @EnableScheduling
//开启Spring异步线程池
@EnableAsync
//启动注解
@SpringBootApplication(exclude = {
        // RedisAutoConfiguration.class,
        // SecurityAutoConfiguration.class,
        ConfigClientAutoConfiguration.class, ElasticsearchAutoConfiguration.class})

//开启Eureka客户端
//@EnableDiscoveryClient

//开启AOP,proxyTargetClass表示是否使用基于cglib的代理,否则使用JDK基于接口的代理
@EnableAspectJAutoProxy(exposeProxy = true)
public class LearnApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        //最常规的启动方式，使用静态方法的方式
        SpringApplication.run(LearnApplication.class, args);

        //以编程的方式激活profile，
        /*
        SpringApplication application = new SpringApplication();
        //激活指定的profile
        application.setAdditionalProfiles("kafka");
        //关闭Banner显示
        application.setBannerMode(Banner.Mode.OFF);
        //Source为项目中需要指定包的路径（Source不能为空）
        application.setSources(new HashSet<>(Collections.singletonList("com.springboot.learn")));
        application.run(args);
        */
    }
}