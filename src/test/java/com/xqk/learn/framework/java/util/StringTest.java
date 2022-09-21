package com.xqk.learn.framework.java.util;

import org.junit.Test;

/**
 * @author 熊乾坤
 * @date 2020-03-13 16:05
 */
public class StringTest {

    @Test
    public void testSubString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            sb.append("a");
        }
        System.out.println(sb.toString());
        System.out.println(sb.substring(0, 64));
        sb.deleteCharAt(0);
        if (sb.length() > 64) {
            System.out.println(sb.substring(0, 64));
        }
        System.out.println(sb.toString());
    }
}
