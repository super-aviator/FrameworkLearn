package com.xqk.learn.springboot.core.ioc.factorybean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class FactoryBeanTest {
    @Autowired
    private MyBean myBean;

    @Test
    public void test(){
        log.info(myBean.toString());
    }
}
