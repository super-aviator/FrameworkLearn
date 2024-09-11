package com.xqk.lean.framework.springboot.core.event.listener;

import com.xqk.lean.framework.springboot.core.event.message.MyApplicationEventMessage;
import com.xqk.lean.framework.springboot.core.event.message.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author 熊乾坤
 * @since 2020-12-20 13:47
 */
@Slf4j
@Component
@Profile("event")
public class MyEventListener {
    @Async
    @EventListener(condition = "#myMessage.messageType%2==0L")
    public void onEvenMessage(MyMessage myMessage) {
        log.info("监听到messageType为偶数的事件[{}]", myMessage);
    }

    @Async
    @EventListener(condition = "#myMessage.messageType%2!=0L")
    public void onNotEvenMessage(MyMessage myMessage) {
        log.info("监听到messageType为奇数的事件[{}]", myMessage);
    }

    @Async
    @EventListener
    public void onApplicationMessage(MyApplicationEventMessage myMessage) {
        log.info("监听到ApplicationEvent事件[{}]", myMessage);
    }
}
