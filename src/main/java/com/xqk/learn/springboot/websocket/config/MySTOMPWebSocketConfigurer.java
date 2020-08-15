package com.xqk.learn.springboot.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author 熊乾坤
 * @date 2020-08-14 10:35
 */
@Configuration
@EnableWebSocketMessageBroker
public class MySTOMPWebSocketConfigurer implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //开启一个WebSocket端点，客户端可以直接连接到此端点（在转发和订阅中，都需要首先连接到此端点）
        registry.addEndpoint("/stomp/test")
                .setAllowedOrigins("*");
        //如果使用Stomp的方式，则不能指定withSockJS，否则会连不上，前端报异常
        //.withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //指定所有Broker的前缀
        registry.setApplicationDestinationPrefixes("/web-socket")
                //开启一个broker
                .enableSimpleBroker("/topic");
    }
}
