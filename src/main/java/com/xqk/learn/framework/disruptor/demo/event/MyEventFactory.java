package com.xqk.learn.framework.disruptor.demo.event;

import com.lmax.disruptor.EventFactory;
import com.xqk.learn.framework.disruptor.demo.event.MyEvent;

/**
 * LogEventFactory
 *
 * @author xiongqiankun
 * @since 2022/9/23 18:32
 */
public class MyEventFactory implements EventFactory<MyEvent> {
    @Override
    public MyEvent newInstance() {
        return new MyEvent();
    }
}
