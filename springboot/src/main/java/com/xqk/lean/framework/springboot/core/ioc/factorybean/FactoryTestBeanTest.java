package com.xqk.lean.framework.springboot.core.ioc.factorybean;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class FactoryTestBeanTest {
    @Autowired
    private MyBean myBean;

    @Test
    public void test(){
        log.info(myBean.toString());
    }
}
