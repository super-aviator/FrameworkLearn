package com.xqk.learn.springboot.quartz;

import com.xqk.learn.springboot.quartz.jobs.MyJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 熊乾坤
 * @date 2020-02-18 21:19
 */
@Configuration
public class MyScheduleTaskService {

    @Bean("mySimpleJobDetail1")
    public JobDetail getJobDetail1() {
        return JobBuilder
                .newJob(MyJob.class)
                .withIdentity("abc", "def")
                .withDescription("测试Quartz定时任务1")
                //.withIdentity("MyJob1", "group1")
                .usingJobData("key1", "value1")
                //这行必须要加上，不然会报错
                .storeDurably(true)
                .build();
    }
    //
    //@Bean("mySimpleTrigger1")
    //public Trigger getTrigger1() {
    //    return TriggerBuilder
    //            .newTrigger()
    //            .startNow()
    //            .withSchedule(
    //                    SimpleScheduleBuilder
    //                            .simpleSchedule()
    //                            .withIntervalInHours(1)
    //                            .withRepeatCount(0))
    //            .withIdentity("myTrigger", "def")
    //            //通过指定jobKey，来将Job与Trigger绑定
    //            //.forJob("abc", "def")
    //            .forJob(getJobDetail1())
    //            .build();
    //}

    //@Bean
    //public JobDetailFactoryBean getJobDetail3(){
    //    JobDetailFactoryBean jobDetailFactoryBean =new JobDetailFactoryBean();
    //    jobDetailFactoryBean.setJobClass(HelloWorldJob.class);
    //    jobDetailFactoryBean.setName("job11");
    //    jobDetailFactoryBean.setBeanName("jobBean1");
    //    jobDetailFactoryBean.setDurability(true);
    //    jobDetailFactoryBean.afterPropertiesSet();
    //    return jobDetailFactoryBean;
    //}
    //
    //@Bean
    //public SimpleTrigger getTrigger3() {
    //    SimpleTriggerFactoryBean simpleTriggerFactoryBean=new SimpleTriggerFactoryBean();
    //    simpleTriggerFactoryBean.setBeanName("triggerBean1");
    //    simpleTriggerFactoryBean.setName("trigger11");
    //    simpleTriggerFactoryBean.afterPropertiesSet();
    //    simpleTriggerFactoryBean.setJobDetail(getJobDetail3().getObject());
    //    return simpleTriggerFactoryBean.getObject();
    //}


/*    @Bean("mySimpleJobDetail2")
    public JobDetail getJobDetail2() {
        return JobBuilder
                .newJob(MyJob.class)
                .withDescription("测试Quartz定时任务2")
                .withIdentity("MyJob2", "group1")
                .usingJobData("key1", "value1")
                //这行必须要加上，不然会报错
                .storeDurably(true)
                .build();
    }

    @Bean("mySimpleTrigger2")
    public Trigger getTrigger2() {
        return TriggerBuilder
                .newTrigger()
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder
                                .repeatSecondlyForever(30)
                                .repeatForever())
                .forJob(getJobDetail2())
                .build();
    }*/
}
