package com.xqk.learn.springboot.util.regex;

import org.springframework.util.Assert;

import java.util.regex.Pattern;

public class MatchFileSuffix {
    private static final Pattern PATTERN = Pattern.compile("(txt|word|bmp|jpg|gif|png|mp4)");

    public static void main(String[] args) {
        Assert.isTrue(PATTERN.matcher("txt").matches(), "");
        Assert.isTrue(PATTERN.matcher("word").matches(), "");
        Assert.isTrue(PATTERN.matcher("bmp").matches(), "");
        Assert.isTrue(PATTERN.matcher("jpg").matches(), "");
        Assert.isTrue(PATTERN.matcher("gif").matches(), "");
        Assert.isTrue(PATTERN.matcher("png").matches(), "");
        Assert.isTrue(PATTERN.matcher("mp4").matches(), "");
    }
}
