package com.xqk.learn.springboot.mvc.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author 熊乾坤
 */
@RestController
@RequestMapping("/uriComponent")
@Slf4j
public class UriComponentController {
    /**
     * 使用UriComponentBuilder的静态方法从字符串构造URI,可以添加自定义路径或者查询参数，指定Schema(http/https)
     *
     * @return JSONObject
     */
    @GetMapping("/query")
    public JSONObject uriComponent() {
        UriComponents uc = UriComponentsBuilder.fromUriString("http://localhost:8080/path")
//                path在build之前，queryParam在build之前
                .path("/index").queryParam("id", 123, 234).build();

        JSONObject result = new JSONObject();
        result.put("toString", uc.toString());
        result.put("encode-toString", uc.encode().toString());
        result.put("encode-toUriString", uc.encode().toUriString());
        return result;
    }

    /**
     * 使用UriComponentBuilder的静态方法从字符串构造URI
     *
     * @return JSONObject
     */
    @GetMapping("/pathVariable")
    public JSONObject uriComponentWithPathVariable() {
        UriComponents uc = UriComponentsBuilder.fromUriString("http://localhost:8080/path/{id}/index/{detail}")
//                填充路径参数在build方法之后，并且填充是按照顺序来的
//                .build().expand(234, 123);
//                也可以一步到位
                .buildAndExpand(234, 123);

        JSONObject result = new JSONObject();
        result.put("toString", uc.toString());
        result.put("encode-toString", uc.encode().toString());
        result.put("encode", uc.encode());
        return result;
    }
}
