package com.xqk.learn.framework.springboot.core.scheduling;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 手动配置Spring Schedule线程池
 * 使用注解@EnableScheduling开启配置
 *
 * @author 熊乾坤
 * @since 2019-9-15
 */

@Configuration
//public class ScheduleTaskConfig implements SchedulingConfigurer {
public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ScheduleTaskConfig {
    //@Override
    //public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    //    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    //    //此方法必须调用，否则会抛出ThreadPoolTaskScheduler未初始化异常
    //    scheduler.initialize();
    //    scheduler.setPoolSize(8);
    //    scheduler.setThreadNamePrefix("aviator-scheduling");
    //    scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    //    taskRegistrar.setScheduler(scheduler);
    //}

    @Bean("taskScheduler")
    public TaskScheduler taskExecutor() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        //此方法必须调用，否则会抛出ThreadPoolTaskExecutor未初始化异常
        executor.initialize();
        executor.setThreadNamePrefix("aviator-taskScheduling-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
