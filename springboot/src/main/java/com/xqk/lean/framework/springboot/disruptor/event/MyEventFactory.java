package com.xqk.lean.framework.springboot.disruptor.event;

import com.lmax.disruptor.EventFactory;

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
