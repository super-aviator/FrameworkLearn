package com.xqk.learn.framework.logback;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


/**
 * @author 熊乾坤
 * @since 2019-9-3
 */
@Slf4j
public class FormatLogMsgTest {

    /**
     * 打印log日志时，使用{}占位符的方式比字符串拼接的方式性能更好，只有在需要打印该级别的日志信息的时候，
     * 才会去格式化日志信息。也就是说在这种情况下，如果禁止了日志的打印，也不会有构建参数上的性能消耗。
     * 这是因为logback首先会对打印级别和有效级别进行检查，如果满足打印的条件，才会将消息封装在LogEvent中
     * 传递给Appender,而只有当日志信息传递给了附加的 appender 时才会被格式化，而且格式化日志信息的组件也
     * 是被优化过的。
     */
    @Test
    public void logMsgFormatTest() {
        //下面两行输出的结果是一样的，但是一旦禁止日志打印，第二个变量的性能至少比第一个变量好上 30 倍。
        log.info("hello" + "log");
        log.info("hello {}", "log");

        //多个参数可以使用数组的方式
        Object[] args = {"a", "b", "c"};
        log.info("hello,{},this is {},this is {}", args);
    }
}
