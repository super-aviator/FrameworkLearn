package com.xqk.learn.framework.websocket.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * @author 熊乾坤
 * @since 2020-08-20 15:16
 */
@Slf4j
@Component
public class DisConnectionListener implements ApplicationListener<SessionDisconnectEvent> {
    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        log.info("stomp监听到客户端断开链接");
    }
}
