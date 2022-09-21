package com.xqk.learn.framework.springboot.core.event.message;

import org.springframework.context.ApplicationEvent;

/**
 * 定义一个事件类，用于接收和发送消息
 *
 * @author 熊乾坤
 * @since 2020-12-20 0:03
 */
public class MyApplicationEventMessage extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyApplicationEventMessage(Object source) {
        super(source);
    }
}
