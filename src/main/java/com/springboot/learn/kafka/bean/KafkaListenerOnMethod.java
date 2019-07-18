package com.springboot.learn.kafka.bean;

import com.springboot.learn.common.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * The type Kafka listener on method.
 *
 * @author 熊乾坤 <p> Kafka一共有一个主题test,该test主题有两个分区。 该test主题有两个消费者组：springboot-learn1和springboot-learn2，每个消费者组分别有两个分区。
 */
@Slf4j
@Component
@Profile("kafka")
public class KafkaListenerOnMethod {

    /**
     * KafkaListener注解的方法，可以注入Consumer，对提交偏移量进行精确的控制
     *
     * @param record   消息对象
     * @param consumer Kafka Consumer对象
     */
    @KafkaListener(topics = "test", groupId = "springboot-learn1")
    @SuppressWarnings("unused")
    public void consume1Partition1(ConsumerRecord<String, UserDTO> record, Consumer consumer) {
        log.info("group:{} partition:{} consume a UserDTO msg:{}", "springboot-learn1", record.partition(), record.value());
    }

    /**
     * 经过测试，接收UserDTO类型的消息，也可以接收String的消息，前提时是不抓换为UserDTO类型
     *
     * @param record 消息对象
     */
    @KafkaListener(topics = "test", groupId = "springboot-learn1")
    public void consume1Partition2(ConsumerRecord<String, UserDTO> record) {
        log.info("group:{} partition:{} consume a UserDTO msg:{}", "springboot-learn1", record.partition(), record.value());
//        log.info(record.value().getAddress());  //报错！！！！！
    }
}