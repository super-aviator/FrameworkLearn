package com.xqk.learn.springboot.websocket.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 熊乾坤
 * @date 2020-08-14 13:26
 */
@Slf4j
@RestController
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * 注意：如果客户端的destination地址为web-socket/stomp/side时,则会自动映射到注解@MessageMapping的value为stomp/side的方法中去（去掉前缀web-socket中）
     * 该方法的返回值会自动推送到了/topic/stomp/side的Broker中去，从而实现广播的功能。
     * <p>
     * 注意：@SendTO注解会将方法的返回值重定向到指定的broker中，不使用默认的转发规则
     *
     * @param message 客户端发送的消息
     * @return 想要广播的消息
     */
    @MessageMapping("stomp/side")
    @SendTo("/topic")
    public Message<String> getMessage(Message<String> message) {
        log.info("stomp服务端接收到消息：{}", message.getPayload());
        return message;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody WebSocketMessage message) {
        log.info("向{}发送消息：{}", "topic", message.getMessage());

        //手动将消息发送至指定的topic中去，路径必要匹配（客户端的订阅地址需要添加前缀web-socket）
        simpMessagingTemplate.convertAndSend("/topic", message);
    }

    @Data
    public static class WebSocketMessage {
        private String message;
    }
}
