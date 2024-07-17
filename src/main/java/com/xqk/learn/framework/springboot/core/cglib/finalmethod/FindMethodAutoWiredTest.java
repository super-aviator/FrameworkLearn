package com.xqk.learn.framework.springboot.core.cglib.finalmethod;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class FindMethodAutoWiredTest {
    @Autowired
    FinalMethodClass finalMethodClass;

    @Test
    public void test() {
        finalMethodClass.finalMethod();
    }
}
