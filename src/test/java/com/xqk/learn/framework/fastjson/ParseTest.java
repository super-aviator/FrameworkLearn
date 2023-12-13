package com.xqk.learn.framework.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xqk.learn.framework.fastjson.pojo.Cla;
import org.junit.Test;

/**
 * 1. FastJSON转换的时候，会使用大小写不敏感的方式对字段名进行绑定
 * 2. FastJSON支持yyyyMMddHHmmss这种数据格式
 *
 * @author 熊乾坤
 * @since 2020-03-08 20:15
 */
public class ParseTest {

    @Test
    public void testFieldBind() {
        String jsonString = "{\"UserID\":\"123\",\"Name\":\"熊乾坤\",\"Age\":20}";
        Cla cla = JSONObject.parseObject(jsonString, Cla.class);
        System.out.println(cla);
    }

    @Test
    public void testDateTime() {
        String jsonString = "{\"UserID\":\"123\",\"Name\":\"熊乾坤\",\"Age\":20,\"birthday\":\"20200310220910\"}";
        System.out.println(JSON.parseObject(jsonString).getDate("birthday"));
    }
}
