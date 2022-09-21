package com.xqk.learn.framework.websocket.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

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
     * 注意：如果客户端的destination地址为web-socket/stomp/side时,则会自动映射到注解@MessageMapping的value为stomp/side的方法中去（去掉前缀/web-socket中）
     * 该方法的返回值会自动推送到了/topic/stomp/side的Broker中去，从而实现广播的功能。
     * <p>
     * 注意：1.@SendTO注解会将方法的返回值重定向到指定的broker中，不使用默认的转发规则
     * SessionSubscriptionRegistry
     *
     * @param message 客户端发送的消息
     * @return 想要广播的消息
     */
    @MessageMapping("stomp/side")
    @SendTo("/web-socket/subscribe/1")
    public Message<String> getMessage(Message<String> message, MessageHeaders headers) {
        log.info("stomp服务端接收到消息：{}，并将消息转发到destination：[{}]", message.getPayload(), "/subscribe/1");
        return message;
    }

    /**
     * 注意：@SubscribeMapping注解只会处理前端发送的SUBSCRIBE的请求，不会处理SENG请求
     */
    @SubscribeMapping("subscribe/{id}")
    public void userSubscribe(@DestinationVariable String id) {
        log.info("stomp服务接受到前端订阅信息，订阅路径：[{}]", id);
    }

    /**
     * 注意：@MessageMapping支持ant风格的路径
     *
     * @param message 客户端发送的消息
     */
    @MessageMapping("stomp/ant/**")
    public void getMessageWithAntPattern(Message<String> message) {
        log.info("stomp服务端接收到消息：{}", message.getPayload());
    }

    /**
     * 注意：@MessageMapping也支持路径参数,需要使用DestinationVariable去解析
     *
     * @param message 客户端发送的消息
     */
    @MessageMapping("stomp/variable/{abc}")
    public void getMessageWithDestinationVariable(@DestinationVariable("abc") String variable, Message<String> message) {
        log.info("stomp服务端接收到路径参数：[{}]发送的消息：[{}]", variable, message.getPayload());
    }

    @PostMapping("/send")
    public void sendMessage(@Validated @RequestBody WebSocketMessage message, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return;
        }
        String destination = "/web-socket/subscribe/" + message.getTopicSuffix();
        log.info("向[{}]发送消息：[{}]", destination, message.getMessage());

        //手动将消息发送至指定的topic中去，路径必要匹配（客户端的订阅地址需要添加前缀web-socket）
        simpMessagingTemplate.convertAndSend(destination, message.getMessage());
    }

    @Data
    public static class WebSocketMessage {
        @NotNull
        private String topicSuffix;

        @NotNull
        private String message;
    }
}
