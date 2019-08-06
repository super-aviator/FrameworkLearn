package com.springboot.learn.mvc.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * run方法中，包含了程序启动时的args参数
 * 在应用上下文初始化之后，执行run方法
 *
 * @author Aviator
 */
@Slf4j
@Order(3)
@Component
public class ThirdCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("在SpringBoot启动成功之前，执行了CommandLineRunnerBean中run方法的代码------3");
    }
}
