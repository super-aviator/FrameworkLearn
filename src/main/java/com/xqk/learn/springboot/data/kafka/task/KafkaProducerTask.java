package com.xqk.learn.springboot.data.kafka.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * KafkaProducerTask
 *
 * @author xiongqiankun
 * @since 2022/8/18 10:15
 */
@Slf4j
@Component
@Profile("kafka")
@RequiredArgsConstructor
public class KafkaProducerTask {
    private long i = 0;
    private final KafkaTemplate<String,String> kafkaProducer;
    public static final String KAFKA_CONCURRENCY_TEST_TOPIC = "kafka_concurrency_test_topic";
    public static final String KAFKA_TTL_TOPIC = "kafka_ttl_topic";
    private static final AtomicLong COUNTER = new AtomicLong(0);


    @Scheduled(fixedDelay = 10000)
    public void sendMessageTo() {
        long message = COUNTER.addAndGet(1L);
        kafkaProducer.send(KAFKA_CONCURRENCY_TEST_TOPIC, 0, "test", String.valueOf(message));
        i = ++i > (kafkaProducer.partitionsFor(KAFKA_CONCURRENCY_TEST_TOPIC).size() - 1) ? 0 : i;
        log.info("[{}] produce a message [{}]", KAFKA_CONCURRENCY_TEST_TOPIC, message);
    }

    @Scheduled(fixedDelay = 10000)
    public void sendMessageToTTL() {
        var message = COUNTER.addAndGet(1L);
        var messageStr = String.valueOf(message);
        var record = new ProducerRecord<String,String>(KAFKA_TTL_TOPIC, 0, null, messageStr,
                new RecordHeaders().add(new RecordHeader("ttl", String.valueOf(1000).getBytes())));
        kafkaProducer.send(record);
        log.info("[{}] produce a message [{}]", KAFKA_TTL_TOPIC, message);
    }
}
