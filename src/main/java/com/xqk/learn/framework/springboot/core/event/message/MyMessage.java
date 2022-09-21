package com.xqk.learn.framework.springboot.core.event.message;

import lombok.Data;

/**
 * @author 熊乾坤
 * @since 2020-12-20 13:42
 */
@Data
public class MyMessage {
    private long messageType;
    private String message;

    public MyMessage(long messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }
}
