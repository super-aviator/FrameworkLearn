package com.springboot.learn.kafka.bean;


import com.springboot.learn.common.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * The type Kafka listener on class.
 *
 * @author 熊乾坤
 */
@KafkaListener(topics = "test", groupId = "test-listener2")
@Component
@Slf4j
public class KafkaListenerHandlerAnnotation {
    /**
     * 只接受UserDTO类型的消息,并且设置为默认处理器
     *
     * @param userDTO 消息
     */
    @KafkaHandler(isDefault = true)
    public void consume2Partition1(UserDTO userDTO, ConsumerRecord<String, UserDTO> record) {
        log.info("group:{} partition:{} consume a UserDTO msg:{}", "test-listener2", record.partition(), userDTO);
    }

    /**
     * 只接受类型为String的消息
     *
     * @param data 消息
     */
    @KafkaHandler
    public void consume2Partition2(String data, ConsumerRecord<String, UserDTO> record) {
        log.info("group:{} partition:{} consume a string msg:{}", "test-listener2", record.partition(), data);
    }
}
