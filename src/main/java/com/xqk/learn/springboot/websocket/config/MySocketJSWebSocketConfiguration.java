package com.xqk.learn.springboot.websocket.config;

import com.xqk.learn.springboot.websocket.handler.MyMessageHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author 熊乾坤
 * @date 2020-08-13 16:29
 */
@Configuration
@EnableWebSocket
public class MySocketJSWebSocketConfiguration implements WebSocketConfigurer {
    private final MyMessageHandler myMessageHandler;

    public MySocketJSWebSocketConfiguration(MyMessageHandler myMessageHandler) {
        this.myMessageHandler = myMessageHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myMessageHandler, "/web-socket/sock-js/test")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
