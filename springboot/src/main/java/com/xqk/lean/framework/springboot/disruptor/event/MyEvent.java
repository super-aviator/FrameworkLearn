package com.xqk.lean.framework.springboot.disruptor.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * LogEvent
 *
 * @author xiongqiankun
 * @since 2022/9/23 18:32
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyEvent {
    private String msg;

    public void clear() {
        msg = null;
    }
}
