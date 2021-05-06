package com.xqk.learn.springboot.core.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 捕获Async注解的方法抛出的异常
 *
 * @author 熊乾坤
 * @date 2020-04-25 12:53
 */
@Slf4j
public class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("caught async exception", ex);
    }
}
