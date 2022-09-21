package com.xqk.learn.framework.json;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The type Json object test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
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
