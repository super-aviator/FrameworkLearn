package com.xqk.learn.framework.disruptor.demo.WorkHandler;

import com.lmax.disruptor.WorkHandler;
import com.xqk.learn.framework.disruptor.demo.event.MyEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * WorkHandler1
 *
 * @author xiongqiankun
 * @since 2022/9/23 19:56
 */
@Slf4j
public class MyWorkHandler1 implements WorkHandler<MyEvent> {
    @Override
    public void onEvent(MyEvent event) throws Exception {
        log.info("WorkHandler1:{} ", event);
    }
}
