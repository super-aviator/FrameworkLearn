package com.springboot.learn.utilityclass;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class FastJsonTest {
    @Test
    public void test() {
        String str = "{\"data\":[{\"type\":\"非机动车\",\"count\":10},{\"type\":\"非机动车\",\"count\":10}],\"file\":123}";
        JSONObject json = JSON.parseObject(str);
        JSONArray jsonArray = json.getJSONArray("data");
    }
}
