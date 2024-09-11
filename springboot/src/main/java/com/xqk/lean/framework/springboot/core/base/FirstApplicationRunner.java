package com.xqk.lean.framework.springboot.core.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ApplicationArguments对象中，封装了启动参数args以及--debug类似的参数
 * run方法中，包含了程序启动时的args参数，在应用上下文初始化之后，执行run方法
 * <p>
 * .@Order注解指定了顺序
 *
 * @author Aviator
 */
@Slf4j
@Order(1)
@Component
public class FirstApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        log.info("在SpringBoot启动成功之前，执行了ApplicationRunnerBean中run方法的代码------1");
    }
}
