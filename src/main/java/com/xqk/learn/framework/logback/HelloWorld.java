package com.xqk.learn.framework.logback;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1. logback的最基础的依赖：slf4j-api、logback-core、logback-classic.jar
 * 2. logback的默认的输出格式为：
 * ---------------------------时间              [线程]    打印级别  logger名    - 日志内容/n
 * ---------------------------%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} -  %msg%n
 * 3. logback-core 是其它两个模块的基础。logback-classic 模块可以看作是 log4j 的一个优化版本，它天然的支持 SLF4J，
 * 所以你可以随意的从其它日志框架（例如：log4j 或者 java.util.logging）切回到 logback。logback-access 可以与
 * Servlet 容器进行整合，例如：Tomcat、Jetty。它提供了 http 访问日志的功能。
 *
 * @author 熊乾坤
 * @since 2019-9-2
 */
public class HelloWorld {
    /**
     * 根据类路径获取Logger对象
     */
    private static final Logger logWithString = LoggerFactory.getLogger("com.xqk.learn.framework.logback.HelloWorld");
    /**
     * 根据Class对象获取Logger对象，和使用字符串方式获取Logger本质上是一样的，内部调用了Class.getName方法，返回的也是完整的类路径
     */
    private static final Logger logWithClass = LoggerFactory.getLogger(HelloWorld.class);
    /**
     * 使用错误的路径字符串，也能获取到Logger对象
     */
    private static final Logger logWithErrorString = LoggerFactory.getLogger("com.xqk.learn.framework.logback.HelloWorld2");

    /**
     * 日志等级记录在ch.qos.logback.classic.Level中
     */
    Level level = Level.INFO;

    public static void main(String[] args) {
        //HelloWorld
        logWithClass.info("Hello World!");

        //每一个类有且仅有一个静态的logger实例，无论是通过Class或者
        System.out.println(logWithClass.equals(logWithString));

        //错误的path
        logWithErrorString.info("error Path");
    }
}
