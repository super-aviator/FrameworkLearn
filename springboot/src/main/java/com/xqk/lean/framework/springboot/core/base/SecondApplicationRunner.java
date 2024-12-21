package com.xqk.lean.framework.springboot.core.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * The type Second application runner bean.
 *
 * @author Aviator
 */
@Slf4j
@Order(2)
@Component
public class SecondApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("在SpringBoot启动成功之前，执行了ApplicationRunnerBean中run方法的代码------2");
    }
}