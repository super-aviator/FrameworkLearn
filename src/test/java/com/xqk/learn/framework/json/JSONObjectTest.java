package com.xqk.learn.framework.json;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The type Json object test.
 */
@Slf4j
@SpringBootTest
public class JSONObjectTest {
    /**
     * Test.
     */
    @Test
    public void test() {
        JSONObject object = new JSONObject();
        log.info("*****" + object.get("Xqk"));
    }
}
