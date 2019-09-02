package com.xqk.learn.springboot.logback;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1. logback的最基础的依赖：slf4j-api、logback-core、logback-classic.jar
 * 2. logback的默认的输出格式为：时间 [方法] 级别 类完整路径 - 日志内容
 *
 * @author 熊乾坤
 * @date 2019-9-2
 */
public class HelloWorld {
    /**
     * 根据Class对象获取Logger对象
     */
    private static final Logger logWithClass = LoggerFactory.getLogger(HelloWorld.class);
    /**
     * 根据类路径获取Logger对象
     */
    private static final Logger logWithString = LoggerFactory.getLogger("com.xqk.learn.springboot.logback.HelloWorld");
    /**
     * 使用错误的路径字符串，也能获取到Logger对象
     */
    private static final Logger logWithErrorString = LoggerFactory.getLogger("com.xqk.learn.springboot.logback.HelloWorld2");

    /**
     * 日志等级记录在ch.qos.logback.classic.Level中
     */
    Level level = Level.INFO;

    public static void main(String[] args) {
//        HelloWorld
        logWithClass.info("Hello World!");

//        每一个类有且仅有一个静态的logger实例，无论是通过Class或者
        System.out.println(logWithClass.equals(logWithString));

//        错误的path
        logWithErrorString.info("error Path");


    }
}
