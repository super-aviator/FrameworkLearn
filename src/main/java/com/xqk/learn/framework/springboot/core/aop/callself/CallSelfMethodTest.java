package com.xqk.learn.framework.springboot.core.aop.callself;

import com.xqk.learn.framework.LearnApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 熊乾坤
 * @since 2021-05-06 16:41
 */
@SpringBootTest(classes = LearnApplication.class)
public class CallSelfMethodTest {
    @Autowired
    private CallSelfMethodService callSelfMethodService;

    @Test
    public void callSelfMethodTest() {
        callSelfMethodService.methodA();
    }
}
