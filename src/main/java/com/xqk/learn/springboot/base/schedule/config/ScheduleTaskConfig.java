package com.xqk.learn.springboot.base.schedule.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 手动配置Spring Schedule线程池
 *
 * @author 熊乾坤
 * @date 2019-9-15
 */

@EnableScheduling
@Configuration
//public class ScheduleTaskConfig implements SchedulingConfigurer {
public class ScheduleTaskConfig {
    //public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    //    taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    //}
}
