package com.xqk.learn.framework.quartz.jobs;

import lombok.Data;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * quartz框架Demo，
 * <p>
 * 1.存储配置值，可以使用jobDetail里面的JobDataMap，或者定义和key相同的成员变量，使用此方式，需要类型一样。
 * 2.JobDetail和Trigger中都可以存储JobDataMap对象，在进行任务调度时JobDataMap存储在JobExecutionContext中非常方便获取。它整合了JobDetail和Trigger里的
 * JobDataMap数据对象，后面的对象会把前面对象相同键值对象的值覆盖。
 *
 * @author 熊乾坤
 * @date 2020-02-17 18:17
 */
@Data
public class HelloWorldJob implements Job {
    /** 设置的这个值成员变量和JobDatamap中都会有 */
    private String name;
    private Integer age;

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        //scheduler需要调用start方法
        scheduler.start();

        JobKey jobKey = new JobKey("d", "e");
        JobDetail myJobDetail = JobBuilder
                .newJob(HelloWorldJob.class)
                .withIdentity("hello", "group1")
                .withDescription("测试quartz")
                //将配置参数存储在JobDataMap中
                .usingJobData("key1", "熊乾坤")
                .usingJobData("key2", 25)
                .usingJobData("name", "熊乾坤")
                .usingJobData("age", 26)
                .withIdentity(jobKey)
                .build();

        TriggerKey triggerKey = new TriggerKey("a", "b");
        Trigger myTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger", "group1")
                .usingJobData("key1", "真正的熊乾坤")
                .usingJobData("key2", 100)
                .usingJobData("name", "真真正正的的熊乾坤")
                .usingJobData("age", 99)
                //设置触发器开始触发的时间 start/endAt设置触发器开始结束触发的时间
                .startAt(DateBuilder.futureDate(15, DateBuilder.IntervalUnit.SECOND))
                .withIdentity(triggerKey)
                //.startAt(DateBuilder.dateOf(9, 46, 0 , 18, 2, 2020))
                //.endAt(DateBuilder.dateOf(9, 46, 51 , 18, 2, 2020))
                //设置触发器优先级，默认为5，只用于不同触发器同一时间触发的优先级
                .withPriority(6)
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(Integer.MAX_VALUE)
                                //repeatForever方法表示循环调用，否则，任务只会被调度一次
                                //.repeatForever()
                                //触发次数设置为2，会触发3次
                                .withRepeatCount(1)
                )
                .build();

        scheduler.scheduleJob(myJobDetail, myTrigger);
        Thread.sleep(20000);
        Date date = scheduler.rescheduleJob(triggerKey, TriggerBuilder
                .newTrigger()
                .withIdentity("trigger", "group1")
                .usingJobData("key1", "真正的熊乾坤")
                .usingJobData("key2", 100)
                .usingJobData("name", "真真正正的的熊乾坤")
                .usingJobData("age", 99)
                //设置触发器开始触发的时间 start/endAt设置触发器开始结束触发的时间
                .startAt(DateBuilder.futureDate(15, DateBuilder.IntervalUnit.SECOND))
                .withIdentity(triggerKey)
                //.startAt(DateBuilder.dateOf(9, 46, 0 , 18, 2, 2020))
                //.endAt(DateBuilder.dateOf(9, 46, 51 , 18, 2, 2020))
                //设置触发器优先级，默认为5，只用于不同触发器同一时间触发的优先级
                .withPriority(6)
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(Integer.MAX_VALUE)
                                //repeatForever方法表示循环调用，否则，任务只会被调度一次
                                //.repeatForever()
                                //触发次数设置为2，会触发3次
                                .withRepeatCount(1)
                )
                .build());
        System.out.println("" + date);
        Thread.sleep(5000);
        date = scheduler.rescheduleJob(triggerKey, TriggerBuilder
                .newTrigger()
                .withIdentity("trigger", "group1")
                .usingJobData("key1", "真正的熊乾坤")
                .usingJobData("key2", 100)
                .usingJobData("name", "真真正正的的熊乾坤")
                .usingJobData("age", 99)
                //设置触发器开始触发的时间 start/endAt设置触发器开始结束触发的时间
                .startAt(DateBuilder.futureDate(15, DateBuilder.IntervalUnit.SECOND))
                .withIdentity(triggerKey)
                //.startAt(DateBuilder.dateOf(9, 46, 0 , 18, 2, 2020))
                //.endAt(DateBuilder.dateOf(9, 46, 51 , 18, 2, 2020))
                //设置触发器优先级，默认为5，只用于不同触发器同一时间触发的优先级
                .withPriority(6)
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(Integer.MAX_VALUE)
                                //repeatForever方法表示循环调用，否则，任务只会被调度一次
                                //.repeatForever()
                                //触发次数设置为2，会触发3次
                                .withRepeatCount(1)
                )
                .build());
        System.out.println("" + date);
        Thread.sleep(1000000);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //此处的key为组名.job名
        JobKey key = context.getJobDetail().getKey();

        JobDataMap jobDetailJobDataMap = context.getJobDetail().getJobDataMap();
        JobDataMap triggerJobDataMap = context.getTrigger().getJobDataMap();
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        System.out.println("------------------------开始任务-------------------------");
        //System.out.println("Hello World!");
        //System.out.println("now:" + LocalDateTime.now());
        //System.out.println("JobKey:" + key);
        //System.out.println("--------------------------------------------------------");
        //
        //System.out.println("map.get(\"key1\"):" + jobDetailJobDataMap.get("key1"));
        //System.out.println("map.get(\"key2\"):" + jobDetailJobDataMap.get("key2"));
        //System.out.println("map.get(\"name\"):" + jobDetailJobDataMap.get("name"));
        //System.out.println("---------------------------------------------------------");
        //
        //System.out.println("triggerJobDataMap.get(\"key1\"):" + triggerJobDataMap.get("key1"));
        //System.out.println("triggerJobDataMap.get(\"key2\")" + triggerJobDataMap.get("key2"));
        //System.out.println("triggerJobDataMap.get(\"name\"):" + triggerJobDataMap.get("name"));
        //System.out.println("---------------------------------------------------------");
        //
        //System.out.println("mergedJobDataMap.get(\"key1\")：" + mergedJobDataMap.get("key1"));
        //System.out.println("mergedJobDataMap.get(\"key2\")：" + mergedJobDataMap.get("key2"));
        //System.out.println("mergedJobDataMap.get(\"name\")：" + mergedJobDataMap.get("name"));
        //System.out.println("---------------------------------------------------------");
        //
        ////从成员变量中取属性，trigger里设置的值会覆盖掉JobDetail中设置的值
        //System.out.println("getName():" + getName());
        //System.out.println("getAge():" + getAge());
        //System.out.println("---------------------------------------------------------");
        //
        //
        //System.out.println("Context.getJobDetail:" + context.getJobDetail());
        System.out.println("------------------------结束任务--------------------------\n");
    }
}
