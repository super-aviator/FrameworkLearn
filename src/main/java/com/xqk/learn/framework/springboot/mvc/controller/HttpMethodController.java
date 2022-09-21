package com.xqk.learn.framework.springboot.mvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.xqk.learn.framework.common.ResponseMessage;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后端接收不同的Http方法传递的参数
 *
 * @author 熊乾坤
 * @date 2019/9/23 14:25
 */
@RestController
@RequestMapping(value = "http_method_test")
public class HttpMethodController {

    /**
     * 经过测试.,PUT方法和GET方法类似，把所有参数都拼接到url后,不能把参数放在body中
     *
     * @param name    name
     * @param friends friends
     * @return HttpEntity
     */
    @PutMapping(value = "/put")
    public HttpEntity<String> putMethod(@RequestParam("name") String name, @RequestParam("friendId") List<String> friends) {
        JSONObject json = new JSONObject();
        json.put("name", "name");
        json.put("friendId", friends);
        return new HttpEntity<>(json.toJSONString());
    }

    /**
     * 经过测试,PUT方法支持路径参数的方式
     *
     * @param name    name
     * @param friends friends
     * @return HttpEntity
     */
    @PutMapping(value = "/put_with_pathVariable/{name}/test/{friendId}")
    public HttpEntity<String> putMethodWithPathVariable(@PathVariable("name") String name, @PathVariable("friendId") String friends) {
        JSONObject json = new JSONObject();
        json.put("name", "name");
        json.put("friendId", friends);
        return new HttpEntity<>(json.toJSONString());
    }

    /**
     * 经过测试,PUT方法支持路径参数的方式
     *
     * @param name    name
     * @param friends friends
     * @return HttpEntity
     */
    @PutMapping(value = "/put_with_pathVariable/{name}/test")
    public HttpEntity<String> putMethodWithPathVariable1(@PathVariable("name") String name, @RequestParam("friendId") List<String> friends) {
        JSONObject json = new JSONObject();
        json.put("name", "name");
        json.put("friendId", friends);
        return new HttpEntity<>(json.toJSONString());
    }

    /**
     * 经过测试，DELETE方法和GET方法类似，把所有参数都拼接到url后,不能把参数放在body中
     *
     * @param name    name
     * @param friends friends
     * @return HttpEntity
     */
    @DeleteMapping(value = "/delete")
    public ResponseMessage<JSONObject> deleteMethod(@RequestParam("name") String name, @RequestParam("friendId") List<String> friends) {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("friendId", friends);

        return ResponseMessage.ok(json);
    }
}