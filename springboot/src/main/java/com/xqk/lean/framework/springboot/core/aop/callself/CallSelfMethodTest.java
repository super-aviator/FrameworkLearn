package com.xqk.lean.framework.springboot.core.aop.callself;

import com.xqk.lean.framework.springboot.SpringbootApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

/**
 * @author 熊乾坤
 * @since 2021-05-06 16:41
 */
@Profile("callself")
@SpringBootTest(classes = SpringbootApplication.class)
public class CallSelfMethodTest {
    @Autowired
    private CallSelfMethodService callSelfMethodService;

    @Test
    public void callSelfMethodTest() {
        callSelfMethodService.methodA();
    }
}
