package com.xqk.learn.framework.logback;

import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * @author 熊乾坤
 * @date 2019-9-3
 */
@Slf4j
public class LoggerContextTest {
    /**
     * LoggerContext可以通过LoggerFactory.getILoggerFactory方法得到
     * <p>
     * 为了释放 logback-classic 所使用的资源，停止使用 logger context是一个好注意。停止 context 将会关闭所有在 logger
     * 上定义的 appender，并且有序的停止正在活动的线程。
     */
    @Test
    public void loggerContextTest() {
        //打印Logger的内部信息，也可以在配置文件的configuration元素后加上debug属性,或者配置一个StatusListener
        ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(loggerContext);

        //调用stop，释放LoggerContext占用的所有资源
        loggerContext.stop();
    }

    /**
     * 测试logback配置文件的自动扫描
     */
    @Test
    public void loggerScanPeriodTest() {
        log.info("Before Sleep");
        try {
            Thread.sleep(80000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("After Sleep");
    }
}
