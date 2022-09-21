package com.xqk.learn.framework.springboot.core.aop.callself;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 熊乾坤
 * @since 2021-05-06 17:05
 */
@Slf4j
@Aspect
@Component
public class CallSelfMethodAspect {
    @Pointcut("execution(* com.xqk.learn.framework.springboot.core.aop.callself.CallSelfMethodService.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundProcess(ProceedingJoinPoint pjp) throws Throwable {
        log.info("before process");
        pjp.proceed();
        log.info("after process");
        return null;
    }
}
