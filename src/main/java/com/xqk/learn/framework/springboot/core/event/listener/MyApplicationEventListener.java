package com.xqk.learn.framework.springboot.core.event.listener;

import com.xqk.learn.framework.springboot.core.event.message.MyApplicationEventMessage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 熊乾坤
 * @since 2020-12-20 0:02
 */
@Component
public class MyApplicationEventListener implements ApplicationListener<MyApplicationEventMessage> {
    @Override
    public void onApplicationEvent(MyApplicationEventMessage event) {
        System.out.println("接收到事件：" + event);
    }
}
