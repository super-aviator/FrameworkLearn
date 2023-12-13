package com.xqk.learn.framework.spring.Thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring 会自动生成一个ThreadPoolTaskExecutor用于异步执行@Async注解的方法；可以通过实现AsyncConfigurer接口来自定义线程池
 * ThreadPoolTaskExecutor默认配置：
 * getActiveCount：0
 * getCorePoolSize：8
 * getKeepAliveSeconds：60
 * getMaxPoolSize：2147483647
 * getThreadPriority：5
 * getThreadNamePrefix：task-
 * <p>
 * <p>
 * Spring会自动生成一个ThreadPoolTaskScheduler用于执行@Scheduled注解的方法；可以通过实现SchedulingConfigurer接口来自定义线程池
 * 默认pool-szie为1
 *
 * @author 熊乾坤
 * @since 2020-03-27 9:46
 */
@ActiveProfiles("default")
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DefaultThreadPoolTest {
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Test
    public void testPrint() {
        System.out.println("getActiveCount：" + threadPoolTaskExecutor.getActiveCount());
        System.out.println("getCorePoolSize：" + threadPoolTaskExecutor.getCorePoolSize());
        System.out.println("getKeepAliveSeconds：" + threadPoolTaskExecutor.getKeepAliveSeconds());
        System.out.println("getMaxPoolSize：" + threadPoolTaskExecutor.getMaxPoolSize());
        System.out.println("getThreadPriority：" + threadPoolTaskExecutor.getThreadPriority());
        System.out.println("getThreadNamePrefix：" + threadPoolTaskExecutor.getThreadNamePrefix());

        System.out.println("getPoolSize：" + threadPoolTaskScheduler.getPoolSize());
    }
}
