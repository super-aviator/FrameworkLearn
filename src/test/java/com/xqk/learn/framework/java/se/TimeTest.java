package com.xqk.learn.framework.java.se;

import org.junit.Test;

import java.util.Date;

/**
 * @author 熊乾坤
 * @since 2020-03-23 11:00
 */
public class TimeTest {

    @Test
    public void testTime() {
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
    }
}
