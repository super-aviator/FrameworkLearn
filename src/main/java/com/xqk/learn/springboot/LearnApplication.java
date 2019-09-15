package com.xqk.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * The type Learn application.
 * 排除定时任务的包
 *
 * @author Aviator
 */
@EnableWebSecurity
@SpringBootApplication
@ComponentScan(basePackages = {"com.xqk.learn.springboot.*"},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASPECTJ,
                pattern = {"com.xqk.learn.springboot.base.schedule.tasks.*"}
        )
)
public class LearnApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        //最常规的启动方式，使用静态方法的方式
        SpringApplication.run(LearnApplication.class, args);


        /*
        //以编程的方式激活profile，
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