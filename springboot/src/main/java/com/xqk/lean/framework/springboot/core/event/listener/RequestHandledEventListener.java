package com.xqk.lean.framework.springboot.core.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * @author 熊乾坤
 * @since 2020-12-20 0:02
 */
@Component
@Profile("event")
public class RequestHandledEventListener implements ApplicationListener<RequestHandledEvent> {
    @Override
    public void onApplicationEvent(RequestHandledEvent event) {
        ServletRequestHandledEvent srhe = (ServletRequestHandledEvent) event;
        System.out.println("监听到Http请求[" + srhe.getRequestUrl() + "]");
    }
}
