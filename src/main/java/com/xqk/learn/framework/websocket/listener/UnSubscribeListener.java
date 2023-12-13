package com.xqk.learn.framework.websocket.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

/**
 * @author 熊乾坤
 * @since 2020-08-17 16:14
 */
@Slf4j
@Component
public class UnSubscribeListener implements ApplicationListener<SessionUnsubscribeEvent> {
    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        log.info("deviceId：[{}]", sha.getSessionAttributes().get("deviceId"));

        log.info("stomp服务接收到取消订阅消息：[{}]", event);
        log.info("stomp服务接收到取消订阅消息：[{}]", event.getMessage().getHeaders());
    }
}