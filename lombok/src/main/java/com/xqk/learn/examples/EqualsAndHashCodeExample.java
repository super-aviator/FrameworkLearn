package com.xqk.learn.examples;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * EqualsAndHashCodeExample
 *
 * @author qiankun.xiong
 * @version 1.0.0
 * @since 2025/1/16 14:37
 */
@Getter
@Setter
@EqualsAndHashCode
public class EqualsAndHashCodeExample {
    private final transient int transientVar = 10;
    private String name;
    private double score;
    private String[] tags;
    @EqualsAndHashCode.Exclude
    private int id;
    @EqualsAndHashCode.Exclude
    private Shape shape;

    @EqualsAndHashCode
    public static class Shape {
        private final String name;

        public Shape(String name) {
            this.name = name;
        }
    }

    @EqualsAndHashCode(callSuper = true)
    public static class Square extends Shape {
        private final int width, height;

        public Square(String name, int width, int height) {
            super(name);
            this.width = width;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        var shape1 = new Square("square1", 1, 2);
        var shape2 = new Square("square2", 1, 2);
        var eah1 = new EqualsAndHashCodeExample();
        eah1.setId(1);
        eah1.setName("shape1");
        eah1.setShape(shape1);

        var eah2 = new EqualsAndHashCodeExample();
        eah1.setId(2);
        eah1.setName("shape2");
        eah1.setShape(shape2);
        System.out.println(eah1.equals(eah2));
    }
}
