package com.xqk.learn.framework.springboot.data.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * KafkaTTLConsumer
 *
 * @author xiongqiankun
 * @since 2022/8/18 11:55
 */
@Slf4j
@Component
@Profile("kafka")
public class KafkaTTLConsumer {
    @KafkaListener(topics = "kafka_ttl_topic", groupId = "kafka_ttl_topic_group")
    public void consume(ConsumerRecord<String,String> record) {
        log.info("[{}] consume a message [{}]", "kafka_ttl_topic_group", record);
    }
}
