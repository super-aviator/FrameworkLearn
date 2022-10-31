package com.xqk.learn.framework.disruptor.demo.handler;

import com.lmax.disruptor.EventHandler;
import com.xqk.learn.framework.disruptor.demo.event.MyEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * LogEventHandler
 *
 * @author xiongqiankun
 * @since 2022/9/23 18:41
 */
@Slf4j
public class MyEventHandler1 implements EventHandler<MyEvent> {
    @Override
    public void onEvent(MyEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("MyEventHandler:{}    sequence:{}   endOfBatch:{}", event, sequence, endOfBatch);
    }
}
