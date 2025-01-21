package com.xqk.learn.examples;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * ConstructExamples
 * 1. 静态字段自动忽略此注解
 *
 * @author qiankun.xiong
 * @version 1.0.0
 * @since 2025/1/16 15:51
 */
public class ConstructExamples {
    @NoArgsConstructor
    public static class NoArgsConstructorExample {
        /** 不生效 */
        @NonNull
        private String name;
        private Integer age;
    }

    @RequiredArgsConstructor
    public static class RequiredArgsConstructorExample {
        @NonNull
        private String name;
        private Integer age;
    }

    @AllArgsConstructor(onConstructor_ = @__({}))
    public static class AllArgsConstructorExample {
        private String name;
        private Integer age;
        private static String staticField;
    }

    public static void testAll() {
        // var noArgs = new NoArgsConstructorExample();
        // var requiredArgs = new RequiredArgsConstructorExample(null);
        var allArgs = new AllArgsConstructorExample("null", null);
    }


    @RequiredArgsConstructor(staticName = "of")
    public static class StaticConstructorExample {
        @NonNull
        private String name;
        private Integer age;
    }

    public static void testStaticConstructor() {
        var staticConstructorExample = StaticConstructorExample.of("static");
    }

    public static void main(String[] args) {
        testAll();
        // testStaticConstructor();
    }
}
