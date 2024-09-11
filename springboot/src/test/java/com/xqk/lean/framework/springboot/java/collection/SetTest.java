package com.xqk.lean.framework.springboot.java.collection;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 熊乾坤
 * @since 2020-03-05 13:32
 */
public class SetTest {
    @Test
    public void testContainAll() {
        Set<String> set1 = new HashSet<>();
        set1.add("1");
        set1.add("2");
        set1.add("3");

        Set<String> set2 = new HashSet<>();
        set2.add("3");
        set2.add("4");
        set2.add("5");
        System.out.println(set1.containsAll(set2));
        System.out.println(set1);
    }
}
