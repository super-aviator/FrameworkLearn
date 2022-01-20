package com.xqk.learn.springboot.debezium;

import com.xqk.learn.springboot.debezium.config.DebeziumConfig;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;
import io.debezium.engine.spi.OffsetCommitPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * DebeziumManager
 *
 * @author xiongqiankun
 * @since 2021/11/30 17:20
 */
@Slf4j
@Component
@Profile("debezium")
@SuppressWarnings({"rawtypes", "unchecked"})
@ConditionalOnProperty(value = "debezium.enabled", havingValue = "true")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DebeziumEngineManager {
    private final DebeziumConfig debeziumConfig;
    private final KafkaTemplate kafkaTemplate;

    private final ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), (r)->new Thread(r, "DebeziumEngineManager"));
    private final AtomicLong serviceSendCounter = new AtomicLong(0);

    @PostConstruct
    public void construct() {
        Properties customerProperties = new Properties();
        customerProperties.putAll(debeziumConfig.getProperties());
        var engine = DebeziumEngine.create(Json.class)
                                   .using(customerProperties)
                                   .notifying(record->{
                                       var message = new ProducerRecord<>(debeziumConfig.getTopic(), record.value());
                                       try {
                                           kafkaTemplate.send(message)
                                                        .get();
                                           log.info("Debezium服务Kafka消息[{}]发送成功", message);
                                           serviceSendCounter.incrementAndGet();
                                       } catch (InterruptedException | ExecutionException e) {
                                           log.error("Debezium服务Kafka发送异常", e);
                                           throw new RuntimeException("Kafka发送异常");
                                       }
                                   })
                                   .using((success, message, error)->{
                                       // 强烈建议加上此部分的回调代码，方便查看错误信息
                                       if (!success) {
                                           // 报错回调
                                           log.error("Debezium服务异常", error);
                                       }
                                   })
                                   .using(OffsetCommitPolicy.always())
                                   .build();
        executor.execute(engine);

        log.info("Debezium服务初始化成功");
    }

    @PreDestroy
    public void destroy() {
        log.info("Debezium服务关闭成功");
    }

    @Scheduled(fixedRate = 60000)
    public void printServiceTask() {
        log.info("############## debezium服务发送消息计数[{}] ##############", serviceSendCounter.get());
    }
}
