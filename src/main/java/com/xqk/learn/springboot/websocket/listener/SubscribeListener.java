package com.xqk.learn.springboot.websocket.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 熊乾坤
 * @date 2020-08-17 16:14
 */
@Slf4j
@Component
public class SubscribeListener implements ApplicationListener<SessionSubscribeEvent> {

    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        @SuppressWarnings("unchecked")
        Set<String> deviceList = (HashSet<String>) sha.getSessionAttributes().getOrDefault("deviceId", new HashSet<String>());
        deviceList.add(event.getMessage().getHeaders().get("simpDestination", String.class));
        sha.getSessionAttributes().put("deviceId", deviceList);
    }
}
