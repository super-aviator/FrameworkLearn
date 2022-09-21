package com.xqk.learn.framework.kafka.consumer;

import com.xqk.learn.framework.springboot.data.kafka.listener.KafkaConcurrencyTest;
import com.xqk.learn.framework.kafka.common.MyKafkaProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * OffsetCommit
 *
 * @author xiongqiankun
 * @since 2022/8/3 13:34
 */
@Slf4j
public class OffsetCommit {
    public static void main(String[] args) {
        var properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, MyKafkaProperties.bootstrap);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "OffsetCommitGroupId");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "OffsetCommitClientId");
        try (var consumer = new KafkaConsumer<String,String>(properties)) {
            consumer.subscribe(Collections.singleton(KafkaConcurrencyTest.KAFKA_CONCURRENCY_TEST_TOPIC));
            while (true) {
                var records = consumer.poll(Duration.ofSeconds(5));
                for (var record : records) {
                    log.info("消费到消息： topic:{}, offset：{}, message:{}, partition:{}", record.topic(),record.offset(), record.value(), record.partition());
                }
                if (!records.isEmpty()) {
                    consumer.commitAsync((offsets, exception)->{
                        if (exception == null) {
                            log.info("下标：{}提交成功", offsets);
                        } else {
                            log.error("下标提交失败", exception);
                        }
                    });
                }
            }
        } catch (WakeupException e) {
            log.error("", e);
        }
    }
}
