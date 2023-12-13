package com.xqk.learn.framework.fastjson.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author 熊乾坤
 * @since 2020-05-25 13:41
 */
@Data
public class Obj1 {
    @JSONField(name = "name")
    private String name;

    @JSONField(name = "name")
    private String usedName;
}
