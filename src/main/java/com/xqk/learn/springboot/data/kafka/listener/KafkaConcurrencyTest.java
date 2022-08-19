package com.xqk.learn.springboot.data.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * 测试并发消费kafka分区数据
 *
 * @author 熊乾坤
 * @date 2020-11-18 14:51
 */
@Slf4j
@Component
@Profile("kafka")
public class KafkaConcurrencyTest{
    public static final String KAFKA_CONCURRENCY_TEST_TOPIC = "kafka_concurrency_test_topic";
    private static final String GROUP_ID = "kafkaConcurrencyTestGroupId";

    @KafkaListener(topics = KAFKA_CONCURRENCY_TEST_TOPIC, groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = KAFKA_CONCURRENCY_TEST_TOPIC, partitions = {"0"})})
    public void concurrentConsumer0(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-0", record.value());
    }

    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = KAFKA_CONCURRENCY_TEST_TOPIC, partitions = {"1"})})
    public void concurrentConsumer1(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-1", record.value());
    }

    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = KAFKA_CONCURRENCY_TEST_TOPIC, partitions = {"2"})})
    public void concurrentConsumer2(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-2", record.value());
    }

    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = KAFKA_CONCURRENCY_TEST_TOPIC, partitions = {"3"})})
    public void concurrentConsumer3(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-3", record.value());
    }

    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = KAFKA_CONCURRENCY_TEST_TOPIC, partitions = {"4"})})
    public void concurrentConsumer4(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-4", record.value());
    }

    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = KAFKA_CONCURRENCY_TEST_TOPIC, partitions = {"5"})})
    public void concurrentConsumer5(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-5", record.value());
    }
}
