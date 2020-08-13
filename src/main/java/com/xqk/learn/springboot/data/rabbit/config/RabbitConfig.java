package com.xqk.learn.springboot.data.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 熊乾坤
 * @date 2020-08-03 9:18
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue getQueue1() {
        return new Queue("queue1", true);
    }

    @Bean
    public Exchange getExchange1() {
        return new DirectExchange("exchange1", true, false);
    }

    @Bean
    public Binding getBinding() {
        return BindingBuilder.bind(getQueue1()).to(getExchange1()).with("routing1").noargs();
    }
}
