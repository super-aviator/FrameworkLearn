package com.xqk.learn.springboot.base.schedule.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * 手动配置Spring Schedule线程池
 *
 * @author 熊乾坤
 * @date 2019-9-15
 */

@EnableScheduling
//@Configuration
//implements SchedulingConfigurer
public class ScheduleTaskConfig {

    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
}
