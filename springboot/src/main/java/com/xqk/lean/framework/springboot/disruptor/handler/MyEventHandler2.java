package com.xqk.lean.framework.springboot.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.xqk.lean.framework.springboot.disruptor.event.MyEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * MySequenceEventHandler
 *
 * @author xiongqiankun
 * @since 2022/9/23 19:40
 */
@Slf4j
public class MyEventHandler2 implements EventHandler<MyEvent> {
    @Override
    public void onEvent(MyEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("MySequenceEventHandler2:{}    sequence:{}   endOfBatch:{}", event, sequence, endOfBatch);
    }
}
