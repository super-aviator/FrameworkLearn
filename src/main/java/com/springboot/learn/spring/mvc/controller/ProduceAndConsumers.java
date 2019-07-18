package com.springboot.learn.spring.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Produce and consumers.
 *
 * @author 熊乾坤  <p> GetMapping注解中，produces元素的值必须匹配请求的Accept头部 consumers元素的值必须匹配请求的Content-Type头部 可以在postman中进行测试。
 */
@RestController("/produce-and-consumers")
public class ProduceAndConsumers {
    /**
     * Produce and consumer response entity.
     *
     * @return the response entity
     */
    @GetMapping(produces = "text/plain", consumes = "application/json")
    public ResponseEntity<String> produceAndConsumer() {
        return new ResponseEntity<>("我接收到你的请求了，你收到我的响应了吗，弟弟萌？", HttpStatus.OK);
    }
}
