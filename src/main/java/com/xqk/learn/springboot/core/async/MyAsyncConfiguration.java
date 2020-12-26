package com.xqk.learn.springboot.core.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 熊乾坤
 * @date 2020-02-21 17:06
 */
@Configuration
public class MyAsyncConfiguration implements AsyncConfigurer {
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //此方法必须调用，否则会抛出ThreadPoolTaskExecutor未初始化异常
        executor.initialize();
        executor.setAllowCoreThreadTimeOut(true);
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("aviator-task");
        executor.setKeepAliveSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    //@Bean("taskExecutor")
    //public TaskExecutor taskExecutor() {
    //    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //    //此方法必须调用，否则会抛出ThreadPoolTaskExecutor未初始化异常
    //    executor.initialize();
    //    executor.setCorePoolSize(10);
    //    executor.setMaxPoolSize(20);
    //    executor.setQueueCapacity(200);
    //    executor.setKeepAliveSeconds(60);
    //    executor.setThreadNamePrefix("aviator-taskExecutor-");
    //    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    //    return executor;
    //}
}
