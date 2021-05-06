package com.xqk.learn.springboot.aop.callself;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AOP无法代理方法中自调用的方法，有以下三种解决方法：
 *
 * @author 熊乾坤
 * @since 2021-05-06 17:00
 */
@Slf4j
@Component
public class CallSelfMethodServiceImpl implements CallSelfMethodService {
    @Autowired
    private CallSelfMethodService callSelfMethodService;

    @Override
    public void methodA() {
        log.info("methodA");
        log.info("------------------------------------");
        //此方法调用没有被AOP增强
        methodB();
        log.info("------------------------------------");
        //方法一：使用自我注入的对象上调用方法可以被AOP增强
        callSelfMethodService.methodB();
        log.info("------------------------------------");
        //方法二：从AopContext获取暴露的代理类，调用代理类的方法。需要@EnableAspectJAutoProxy注解的exposeProxy为true
        ((CallSelfMethodService) AopContext.currentProxy()).methodB();
    }

    @Override
    public void methodB() {
        log.info("methodB");
    }
}
