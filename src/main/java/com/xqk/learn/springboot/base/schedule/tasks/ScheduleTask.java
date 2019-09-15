package com.xqk.learn.springboot.base.schedule.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Aviator
 * @date 2019-9-15
 */
@Component
@Slf4j
public class ScheduleTask {

    //@Scheduled(fixedDelay = 5000)
    //public void execute1(){
    //    log.info("schedule task with fixedDelay---->{} milliseconds",5000);
    //}

    //@Scheduled(fixedDelay = 5000)
    //public void execute2() throws InterruptedException {
    //    Thread.sleep(3000);
    //    log.info("schedule sleep task with fixedDelay---->{} milliseconds",5000);
    //}
    //
    //@Scheduled(fixedRate = 5000)
    //public void execute3() throws InterruptedException {
    //    Thread.sleep(3000);
    //    log.info("schedule sleep task with fixedRate---->{} milliseconds",5000);
    //}

    //@Scheduled(fixedDelay = 5000,initialDelay = 10000)
    //public void execute4() {
    //    log.info("schedule task with fixedDelay---->{} milliseconds,{} initialDelay",5000,5000);
    //}
    //
    //@Scheduled(fixedRate = 5000,initialDelay = 10000)
    //public void execute5() {
    //    log.info("schedule task with fixedRate---->{} milliseconds,{} initialDelay",5000,5000);
    //}

    //@Scheduled(fixedDelay = 5000,fixedRate = 5000)
    //public void execute6(){
    //    log.info("schedule task with fixedDelay fixedRate---->{} milliseconds,{} milliseconds",5000,5000);
    //}

    /**
     * 每隔15秒钟执行一次，/前面的*千万不能少
     */
    @Scheduled(cron = "*/15 * * * * ?")
    public void execute7() {
        log.info("schedule task with cron expression---->{}", "*/15 * * * * ?");
    }

    /**
     * 每分的第30秒执行一次
     */
    @Scheduled(cron = "30 * * * * ?")
    public void execute8() {
        log.info("schedule task with cron expression---->{}", "30 * * * * ?");
    }

    /**
     * 每天的17:45:30执行一次
     */
    @Scheduled(cron = "30 47 17 * * ?")
    public void execute9() {
        log.info("schedule task with cron expression---->{}", "30 47 17 * * ?");
    }
}
