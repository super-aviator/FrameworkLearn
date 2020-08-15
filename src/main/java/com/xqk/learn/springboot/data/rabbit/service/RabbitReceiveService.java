package com.xqk.learn.springboot.data.rabbit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author 熊乾坤
 * @date 2020-08-03 9:27
 */
@Slf4j
@Service
@Profile("rabbitmq")
public class RabbitReceiveService {
    @RabbitHandler
    @RabbitListener(queues = "queue1")
    public void receiveMessage(String message) {
        log.error("rabbitMQ接收到队列：{}的消息：{}", "queue1", message);
    }
}
