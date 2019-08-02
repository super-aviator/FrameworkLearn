package com.springboot.learn.mvc.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * The type Disposable bean test.
 *
 * @author 熊乾坤  <p> 实现了DisposableBean，在bean被丢弃时，会调用destroy方法。
 */
@Slf4j
@Component
public class DisposableBeanTest implements DisposableBean {

    @Override
    public void destroy() {
        log.info("DisposableBeanTest has been destroyed", DisposableBeanTest.class);
    }
}
