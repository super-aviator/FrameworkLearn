package com.springboot.learn.kafka.bean;

import com.springboot.learn.common.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Objects;

/**
 * The type Kafka producer.
 *
 * @author 熊乾坤 <p> Kafka生产者。
 */
@Component
@Slf4j
@Profile("kafka")
public class KafkaProducer {
    private final KafkaTemplate<String, UserDTO> template;

    private final KafkaTemplate<String, String> strTemplate;

    /**
     * Instantiates a new Kafka producer.
     *
     * @param template    the template
     * @param strTemplate the str template
     */
    @Autowired(required = false)
    public KafkaProducer(KafkaTemplate<String, UserDTO> template, KafkaTemplate<String, String> strTemplate) {
        this.template = template;
        this.strTemplate = strTemplate;
    }

    /**
     * Produce.
     *
     * @param topic the topic
     * @param key   the key
     * @param user  the user
     */
    public void produce(String topic, String key, UserDTO user) {
        log.info("Produce a UserDTO msg:{} to topic:{} key is {}", user.toString(), topic, key);
        if (Objects.isNull(key)) {
            ListenableFuture<SendResult<String, UserDTO>> listenableFuture = template.send(topic, user);
        } else {
            template.send(topic, key, user);
        }
        template.flush();
    }

    /**
     * Produce str.
     *
     * @param topic the topic
     * @param key   the key
     * @param data  the data
     */
    public void produceStr(String topic, String key, String data) {
        log.info("Produce a string msg:{} to topic:{} key is {}", data, topic, key);
        if (Objects.isNull(key)) {
            strTemplate.send(topic, data);
        } else {
            strTemplate.send(topic, key, data);
        }
        strTemplate.flush();
    }

}
