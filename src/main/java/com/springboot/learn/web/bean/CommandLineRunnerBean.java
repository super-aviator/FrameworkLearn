package com.springboot.learn.web.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 在Application启动之前执行某些操作
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
