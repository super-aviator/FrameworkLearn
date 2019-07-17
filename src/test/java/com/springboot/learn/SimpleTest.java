package com.springboot.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class SimpleTest {

    /**
     * null也是对象，可以对null进行强转，结果为null,但是不能将其赋值给基本类型的变量;
     */
    @Test
    public void testCastNull() {
        log.info((String) null);

        Integer i = (Integer) null;
        System.out.println(i);

        int i2 = (Integer) null;
        System.out.println(i2);
    }
}
