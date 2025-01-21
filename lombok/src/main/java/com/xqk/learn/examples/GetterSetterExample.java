package com.xqk.learn.examples;

import lombok.AccessLevel;
import lombok.Setter;

import java.beans.Transient;

/**
 * GetterSetterExample
 *
 * @author qiankun.xiong
 * @version 1.0.0
 * @since 2025/1/21 15:36
 */
public class GetterSetterExample {
    @Setter(value = AccessLevel.PUBLIC, onMethod_ = {@Transient}, onParam_ = {})
    private int age;

    public static void main(String[] args) {

    }
}
