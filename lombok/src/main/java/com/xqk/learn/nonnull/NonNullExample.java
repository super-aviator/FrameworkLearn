package com.xqk.learn.nonnull;

import lombok.Data;
import lombok.NonNull;

/**
 * NonNullLearn
 *
 * @author qiankun.xiong
 * @version 1.0.0
 * @since 2025/1/16 11:06
 */
public record NonNullExample(String name) {
    public NonNullExample(@NonNull String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        var nonNullExample1 = new NonNullExample(null);
        var nonNullExample2 = new NonNullExample("null");
    }
}
