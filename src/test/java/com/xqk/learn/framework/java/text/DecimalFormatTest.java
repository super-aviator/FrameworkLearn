package com.xqk.learn.framework.java.text;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * @author Aviator
 * @date 2019-9-1
 */
@Slf4j
public class DecimalFormatTest {
    private static DecimalFormat df = new DecimalFormat("#.#");

    @Test
    public void test() {
        Assert.assertEquals("" + 3.5, df.format(3.45));
        Assert.assertNotEquals("" + 3.56, df.format(3.55));
    }
}
