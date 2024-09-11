package com.xqk.lean.framework.springboot.core.ioc.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * MyDistructionAwareBeanPostPorcessor
 *
 * @author xiongqiankun
 * @since 2022/10/29 16:02
 */
@Slf4j
@Component
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        log.info("DestructionAwareBeanPostProcessor#postProcessBeforeDestruction");
    }
}
