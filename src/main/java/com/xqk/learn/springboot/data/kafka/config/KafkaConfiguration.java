package com.xqk.learn.springboot.data.kafka.config;

import com.xqk.learn.springboot.kafka.interceptor.TTLConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaConfiguration
 *
 * @author xiongqiankun
 * @since 2022/8/18 13:35
 */
@Configuration
public class KafkaConfiguration {
    // @Bean
    // public ConsumerFactory<?,?> kafkaConsumerFactory() {
    //     Map<String,Object> consumerProperties = new HashMap<>();
    //     consumerProperties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, TTLConsumerInterceptor.class.getName());
    //     return new DefaultKafkaConsumerFactory<>(consumerProperties);
    // }

    /*@Bean
    public ProducerFactory<?, ?> kafkaProducerFactory() {
        Map<String, Object> producerProperties = new HashMap<>();
        producerProperties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MyProducerInterceptor.class.getName());
        DefaultKafkaProducerFactory<?, ?> factory = new DefaultKafkaProducerFactory<>(producerProperties);
        return factory;
    }*/
}
