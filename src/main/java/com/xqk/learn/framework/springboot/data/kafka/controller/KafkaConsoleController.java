package com.xqk.learn.framework.springboot.data.kafka.controller;

import com.xqk.learn.framework.springboot.data.jpa.dto.UserDTO;
import com.xqk.learn.framework.springboot.data.kafka.dto.SendKafkaDTO;
import com.xqk.learn.framework.springboot.data.kafka.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * The type Kafka console controller.
 *
 * @author 熊乾坤 <p> 注意，@Profile注解也可以用在Controller层，如果该profile未激活时，Controller路径不会映射出来。
 */
@RestController
@RequestMapping("/kafka")
@Slf4j
@Profile("kafka")
public class KafkaConsoleController {
    private final KafkaProducer kafkaProducerBean;

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Instantiates a new Kafka console controller.
     *
     * @param kafkaProducerBean the kafka producer bean
     * @param kafkaTemplate     the kafka template
     */
    @Autowired(required = false)
    public KafkaConsoleController(KafkaProducer kafkaProducerBean, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaProducerBean = kafkaProducerBean;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 向指定topic发送消息，当使用@RequestBody报错时，可以使用@ModelAttribute替代
     * <p>
     * As said dknight @RequestBody means use of JSON or XML data with maps your DTO bean.
     * In case of MultipartFile you can't use JSON data so you can't use @RequestBody.
     * Try with @ModelAttribute annotation.
     *
     * @param topic 指定的topic
     * @param key   the key
     * @param user  发送的user对象
     */
    @PostMapping("/send-user")
    public void send(@RequestParam("topic") String topic, String key, @ModelAttribute UserDTO user) throws ExecutionException, InterruptedException {
        log.info("Get a Post request---topic: " + topic + "---msg: " + user);
        kafkaProducerBean.produce(topic, key, user);
    }


    /**
     * Send str.
     */
    @PostMapping("/send-str")
    public void sendStr(@RequestBody SendKafkaDTO sendKafkaDTO) {
        log.info("Get a Post request---topic: " + sendKafkaDTO.getTopic() + "---msg: " + sendKafkaDTO.getData());
        kafkaProducerBean.produceStr(sendKafkaDTO.getTopic(), sendKafkaDTO.getKey(), sendKafkaDTO.getData());
    }

    /**
     * 查询topic的分区信息
     *
     * @param topic 分区
     * @return 分区列表 string
     */
    @GetMapping("/partition/{topic}")
    public String partitionInfo(@PathVariable("topic") String topic) {
        return kafkaTemplate.partitionsFor(topic).toString();
    }
}
