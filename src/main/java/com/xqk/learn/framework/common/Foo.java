package com.xqk.learn.framework.common;

import lombok.Getter;

/**
 * Foo
 *
 * @author xiongqiankun
 * @since 2022/1/25 19:40
 */
@Getter
public class Foo {
    private final String name;

    public Foo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Foo{" + "name='" + name + '\'' + '}';
    }
}
