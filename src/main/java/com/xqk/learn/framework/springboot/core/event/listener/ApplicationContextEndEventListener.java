package com.xqk.learn.framework.springboot.core.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @author 熊乾坤
 * @since 2020-12-20 0:02
 */
@Component
public class ApplicationContextEndEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("监听到ApplicationContext关闭");
    }
}
