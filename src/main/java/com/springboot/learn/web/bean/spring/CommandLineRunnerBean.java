package com.springboot.learn.web.bean.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * run方法中，包含了程序启动时的args参数
 *
 * @author Aviator
 */
@Slf4j
@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.debug(Arrays.toString(args)+"**************************************");
    }
}
