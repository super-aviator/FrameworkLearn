package com.xqk.lean.framework.springboot.kafka.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TTLConsumerInterceptor
 *
 * @author xiongqiankun
 * @since 2022/8/18 9:48
 */
@Slf4j
public class TTLConsumerInterceptor implements ConsumerInterceptor<String,String> {
    @Override
    public ConsumerRecords<String,String> onConsume(ConsumerRecords<String,String> records) {
        var now = System.currentTimeMillis();
        var result = new HashMap<TopicPartition,List<ConsumerRecord<String,String>>>();
        for (var partition : records.partitions()) {
            var record = records.records(partition);
            var filterMessage = record.stream().filter(msg->{
                for (var header : msg.headers()) {
                    if ("ttl".equals(header.key())) {
                        var ttl = Long.parseLong(new String(header.value()));
                        log.info("now:[{}] timestamp:[{}] ttl:[{}].", now, msg.timestamp(), ttl);
                        if (now > msg.timestamp() + ttl) {
                            log.error("[{}] consume a ttl message [{}] has expired.", "TTLConsumerInterceptor", msg.value());
                            return false;
                        }
                    }
                }
                return true;
            }).collect(Collectors.toList());
            if (!filterMessage.isEmpty()) {
                result.put(partition, filterMessage);
            }
        }
        return new ConsumerRecords<>(result);
    }

    @Override
    public void onCommit(Map<TopicPartition,OffsetAndMetadata> offsets) {
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String,?> configs) {
    }
}
