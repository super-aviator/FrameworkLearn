package com.xqk.learn.springboot.data.kafka.bean;

import lombok.Data;

/**
 * @author 熊乾坤
 * @date 2020-05-31 15:32
 */
@Data
public class SendKafkaVO {
    String topic;
    String key;
    String data;
}
