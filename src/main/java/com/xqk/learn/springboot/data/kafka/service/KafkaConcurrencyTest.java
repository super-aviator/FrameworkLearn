package com.xqk.learn.springboot.data.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试并发消费kafka分区数据
 *
 * @author 熊乾坤
 * @date 2020-11-18 14:51
 */
@Slf4j
@Component
@Profile("kafka")
public class KafkaConcurrencyTest implements CommandLineRunner {
    private static final String TOPIC = "kafka_concurrency_test_topic";
    private static final String GROUP_ID = "kafkaConcurrencyTestGroupId";
    private final KafkaTemplate<String, Long> kafkaTemplate;

    public KafkaConcurrencyTest(KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        ProducerThread pt = new ProducerThread(kafkaTemplate, "KafkaProducerTest");
        pt.start();
    }

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = TOPIC, partitions = {"0"})})
    public void concurrentConsumer0(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-0", record.value());
    }

    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = TOPIC, partitions = {"1"})})
    public void concurrentConsumer1(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-1", record.value());
    }

    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = TOPIC, partitions = {"2"})})
    public void concurrentConsumer2(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-2", record.value());
    }

    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = TOPIC, partitions = {"3"})})
    public void concurrentConsumer3(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-3", record.value());
    }

    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = TOPIC, partitions = {"4"})})
    public void concurrentConsumer4(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-4", record.value());
    }

    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = TOPIC, partitions = {"5"})})
    public void concurrentConsumer5(ConsumerRecord<String, Long> record) {
        log.info("[{}] consume a message [{}]", "concurrentConsumer-5", record.value());
    }

    private static class ProducerThread extends Thread {
        private static final AtomicLong COUNTER = new AtomicLong(0L);
        private final KafkaTemplate<String, Long> kafkaProducer;

        private ProducerThread(KafkaTemplate<String, Long> kafkaProducer, String name) {
            super(name);
            this.kafkaProducer = kafkaProducer;
        }

        @Override
        public void run() {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                long message = COUNTER.addAndGet(1L);
                kafkaProducer.send(TOPIC, i, "test", message);
                i = ++i >= 6 ? 0 : i;
                log.info("[{}] produce a message [{}]", super.getName(), message);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log.info("producer is over");
                }
            }
        }
    }
}
