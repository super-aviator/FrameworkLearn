package com.xqk.learn.springboot.core.cglib.finalmethod;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class FindMethodAutoWiredTest {
    @Autowired
    FinalMethodClass finalMethodClass;

    @Test
    public void test() {
        finalMethodClass.finalMethod();
    }
}