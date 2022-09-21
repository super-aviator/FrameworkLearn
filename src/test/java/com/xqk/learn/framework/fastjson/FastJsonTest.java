package com.xqk.learn.framework.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xqk.learn.framework.fastjson.pojo.Obj1;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The type Fast json test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class FastJsonTest {
    /**
     * Test.
     */
    @Test
    public void test() {
        String str = "{\"data\":[{\"type\":\"非机动车\",\"count\":10},{\"type\":\"非机动车\",\"count\":10}],\"file\":123}";
        JSONObject json = JSON.parseObject(str);
        JSONArray jsonArray = json.getJSONArray("data");
    }

    @Test
    public void testField() {
        Obj1 obj1 = new Obj1();
        Obj1 obj2 = new Obj1();
        obj1.setName("熊乾坤");
        obj2.setUsedName("坤坤");

        log.info("test1" + JSON.toJSONString(obj1));
        log.info("test2" + JSON.toJSONString(obj2));
    }
}
