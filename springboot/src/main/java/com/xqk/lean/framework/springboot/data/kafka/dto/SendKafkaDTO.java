package com.xqk.lean.framework.springboot.data.kafka.dto;

import lombok.Data;

/**
 * @author 熊乾坤
 * @since 2020-05-31 15:32
 */
@Data
public class SendKafkaDTO {
    String topic;
    String key;
    String data;
}
