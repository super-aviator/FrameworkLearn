package com.xqk.learn.framework.quartz.jobs;

import com.xqk.learn.framework.springboot.data.jpa.repository.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 1. 如果想要定时任务不并行执行，在job类上加入@DisallowConcurrentExecution注解
 * 2. spring会自动注入ApplicationContext的bean,其他的bean可以直接注入进去
 * 3. 使用jobStore时，如果想要删除任务，需要将数据库表里面持久化的Trigger和JobDetail都删除，否则任务依旧会运行
 *
 * @author 熊乾坤
 * @since 2020-02-18 21:23
 */
@Slf4j
@Profile("quartz")
@Component
@DisallowConcurrentExecution
public class MyJob extends QuartzJobBean {
    private final ApplicationContext applicationContext;

    private final UserJpaRepository userJpaRepository;

    public MyJob(ApplicationContext applicationContext, UserJpaRepository userJpaRepository) {
        this.applicationContext = applicationContext;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("----------Quartz定时任务启动----------");
        log.info("jobKey:{}", context.getTrigger().getJobKey());
        log.info("contain application context:{}", applicationContext != null);
        log.info("contain userJpaRepository:{}", userJpaRepository != null);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("----------Quartz定时任务结束----------");
    }
}
