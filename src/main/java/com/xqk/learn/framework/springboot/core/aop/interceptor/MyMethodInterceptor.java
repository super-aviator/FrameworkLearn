package com.xqk.learn.framework.springboot.core.aop.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * MyMethodInterceptor
 *
 * @author xiongqiankun
 * @since 2022/10/14 9:29
 */
@Slf4j
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info(invocation.toString());
        return invocation.proceed();
    }
}
