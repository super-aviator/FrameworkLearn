package com.xqk.learn.springboot.core.event.publisher;

import com.xqk.learn.springboot.core.event.message.MyApplicationEventMessage;
import com.xqk.learn.springboot.core.event.message.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 每20秒钟定时发送一个事件
 * 默认情况下，事件侦听器将同步接收事件。这意味着publishEvent()方法会阻塞直到所有的监听者都处理完。
 * 这种同步和单线程方法的一个优点是，如果事务上下文可用，它就会在发布者的事务上下文中处理。
 *
 * @author 熊乾坤
 * @since 2020-12-20 0:06
 */
@Slf4j
@Component
public class MyApplicationEventPublisher implements ApplicationEventPublisherAware {
    private final AtomicLong atomicLong = new AtomicLong();
    private ApplicationEventPublisher applicationEventPublisher;

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduledPublishEvent() {
        //发布一个继承了ApplicationEvent接口的事件
        String message = "发布ApplicationEvent事件" + atomicLong.addAndGet(1);
        log.info(message);
        applicationEventPublisher.publishEvent(new MyApplicationEventMessage(message));
        //发布一个任意类型的事件
        message = "发布任意事件" + atomicLong.get();
        log.info(message);
        applicationEventPublisher.publishEvent(new MyMessage(atomicLong.get(), message));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
