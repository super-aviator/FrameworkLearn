package com.xqk.learn.framework.java.se;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * lambda表达式的forEach方法中的return不会使方法返回，而是相当于continue方法
 *
 * @author 熊乾坤
 * @since 2020-03-13 16:53
 */
public class CycleTest {

    @Test
    public void testLambdaCycle() {
        Arrays.stream(Arrays.asList("1", "2", "3").toArray())
                .forEach(i -> {
                    if (i.equals("2")) {
                        return;
                    }
                    System.out.println(i);
                });
    }
}
