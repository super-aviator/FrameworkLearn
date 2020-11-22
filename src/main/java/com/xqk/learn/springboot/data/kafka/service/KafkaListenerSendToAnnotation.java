package com.xqk.learn.springboot.data.kafka.service;

import com.xqk.learn.springboot.data.jpa.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * The type Kafka listener send to annotation.
 *
 * @author 熊乾坤
 */
@Component
@Slf4j
@Profile("kafka")
public class KafkaListenerSendToAnnotation {

    /**
     * 测试@Sendto注解，接收从test主题转发到@Sendto发送的消息
     *
     * @param user the user
     */
    //@KafkaListener(topics = "sendto", groupId = "sendto-listener1")
    public void receiveMsg(UserDTO user) {
        log.info("group:send-to-listener1 consume a UserDTO msg:{} from topic :sendto", user.toString());
    }

    /**
     * test主题的test-listener1消费者组的第二个消费者，将test主题发送的消息接收之后转发到sendto主题。
     * 其中，@Send注解的value元素也可以使用下面两种方式获取，运行时的spEL表达式或者编译时的配置文件：
     * 1. @SendTo("!{request.value()}") // runtime SpEL
     * 2. @SendTo("#{myBean.replyTopic}") // config time SpEL
     *
     * @param user   消息对象
     * @param record the record
     * @return the user dto
     */
    @KafkaListener(topics = "test", groupId = "test-listener1")
    //@SendTo("sendto")
    public UserDTO consume1Partition2(UserDTO user, ConsumerRecord<String, UserDTO> record) {
        log.info("group:test-listener1 partition:{} consume a UserDTO msg:{} from topic:test and then send to topic :{}", record.partition(), user.toString(), "sendto");
        return user;
    }
}