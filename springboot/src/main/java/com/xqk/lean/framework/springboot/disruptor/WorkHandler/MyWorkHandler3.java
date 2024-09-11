package com.xqk.lean.framework.springboot.disruptor.WorkHandler;

import com.lmax.disruptor.WorkHandler;
import com.xqk.lean.framework.springboot.disruptor.event.MyEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * WorkHandler1
 *
 * @author xiongqiankun
 * @since 2022/9/23 19:56
 */
@Slf4j
public class MyWorkHandler3 implements WorkHandler<MyEvent> {
    @Override
    public void onEvent(MyEvent event) throws Exception {
        log.info("WorkHandler3:{} ", event);
    }
}
