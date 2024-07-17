package com.xqk.learn.framework.logback;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log的层级关系
 * <p>
 * <p>
 * log打印的级别
 * 1. 日志的打印级别为 p，Logger实例的级别为 q，如果 p >= q，则该条日志可以打印出来。
 * 2. 日志的等级为：TRACE < DEBUG < INFO < WARN < ERROR
 * 3. 对于一个给定的名为 L 的 logger，它的有效层级为从自身一直回溯到 root logger，直到找到第一个不为空的层级作为自己的层级。
 * 所以为了确保所有的 logger 都有一个层级，root logger 会有一个默认层级 --- DEBUG
 *
 * @author 熊乾坤
 * @since 2019-9-2
 */
public class LogLevelTest {
    /**
     * ROOT是所有Logger的父Logger
     */
    private static final Logger rootLog = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    /**
     * deriveLog是rootLog的子Logger
     */
    private static final Logger deriveLog = LoggerFactory.getLogger("com.xqk.learn.framework.logback.LogLevelTest");

    /**
     * 设置root的日志级别为OFF，可以关闭日志的打印
     */
    @Test
    public void closeLogPrint() {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.OFF);
        deriveLog.info("尝试在关闭日志后打印日志");

        //重新设置日志的有效级别，可以打印
        ch.qos.logback.classic.Logger logger1 = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
        logger1.setLevel(Level.DEBUG);
        logger1.info("尝试在关闭日志后打印日志");
    }

    @Test
    public void extendRootLoggerTest() {
        //只有debug和info能够打印，因为继承了root的Logger的有效级别，默认的打印级别为debug
        deriveLog.trace("trace打印了吗？");
        deriveLog.debug("debug打印了吗？");
        deriveLog.info("info打印了吗？");
    }

    @Test
    public void loggerLevelExtendsTest() {
        //日志打印的有效级别可以继承
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
        logger.setLevel(Level.INFO);
        //会打印
        logger.warn("com.foo logger warn");
        //不会打印
        logger.debug("com.foo logger debug");
        //继承自com.foo
        Logger deriveLogger = LoggerFactory.getLogger("com.foo.ch");
        //会打印
        logger.warn("com.foo.ch logger warn");
        //不会打印
        logger.debug("com.foo.ch logger debug");
    }
}
