package com.springboot.learn.web.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Aviator
 */
@Component
@Slf4j
public class ApplicationRunnerBean implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        log.debug("啦啦啦，啦啦啦，我是卖报的小行家！");
    }
}
