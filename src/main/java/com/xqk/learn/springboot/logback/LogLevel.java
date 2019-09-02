package com.xqk.learn.springboot.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log的层级关系
 * <p>
 * <p>
 * log打印的级别
 * 1. 日志的打印级别为 p，Logger 实例的级别为 q，如果 p >= q，则该条日志可以打印出来。
 * 2. 日志的等级为：TRACE < DEBUG < INFO < WARN < ERROR
 * 3. 对于一个给定的名为 L 的 logger，它的有效层级为从自身一直回溯到 root logger，直到找到第一个不为空的层级作为自己的层级。
 * 所以为了确保所有的 logger 都有一个层级，root logger 会有一个默认层级 --- DEBUG
 *
 * @author 熊乾坤
 * @date 2019-9-2
 */
public class LogLevel {
    /**
     * ROOT是所有Logger的父Logger
     */
    private static final Logger rootLog = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    public static void main(String[] args) {

    }
}
