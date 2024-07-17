package com.xqk.learn.framework.java.text;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试
 *
 * @author 熊乾坤
 * @since 2020-03-05 18:39
 */
public class RegexTest {
    private static final Pattern PERSON_ATTR_PATTERN = Pattern.compile("\\d+[Xx]?");

    /**
     * 简单的身份证号码正则表达式
     */
    @Test
    public void testAllNumber() {
        System.out.println(PERSON_ATTR_PATTERN.matcher("123熊123").matches());
        System.out.println(PERSON_ATTR_PATTERN.matcher("123x").matches());
        System.out.println(PERSON_ATTR_PATTERN.matcher("123熊").matches());
        System.out.println(PERSON_ATTR_PATTERN.matcher("123X").matches());
        System.out.println(PERSON_ATTR_PATTERN.matcher("123").matches());
    }

    private static final String NOT_NUMBER_STRING = "[^0-9]+";

    @Test
    public void testSeparator() {
        String text = ";;..51002313500072你好,.;51002313500071;3xill51004102600011,";
        System.out.println(Arrays.toString(text.split(NOT_NUMBER_STRING)));
    }

    @Test
    public void testDpsType() {
        String type = "(3=苏A00111)and(4=6)and(5=7)and(6=111)and(98=2)and(98=10)";
        Pattern p = Pattern.compile("\\(1=(.+?)\\)");
        Matcher m = p.matcher(type);
        while (m.find()) {
            System.out.println("*" + m.group(1));
        }
    }
}
