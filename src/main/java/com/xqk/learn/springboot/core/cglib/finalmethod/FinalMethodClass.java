package com.xqk.learn.springboot.core.cglib.finalmethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
public class FinalMethodClass {
    private final Bean bean;

    public FinalMethodClass(Bean bean) {
        this.bean = bean;
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public final void finalMethod() {
        log.info(bean.toString());
    }
}