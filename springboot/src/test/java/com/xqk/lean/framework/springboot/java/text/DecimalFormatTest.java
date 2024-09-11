package com.xqk.lean.framework.springboot.java.text;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

/**
 * @author Aviator
 * @since 2019-9-1
 */
@Slf4j
public class DecimalFormatTest {
    private static DecimalFormat df = new DecimalFormat("#.#");

    @Test
    public void test() {
        Assertions.assertEquals("" + 3.5, df.format(3.45));
        Assertions.assertNotEquals("" + 3.56, df.format(3.55));
    }
}
