package com.xqk.learn.springboot.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Produce and consumers.
 *
 * @author 熊乾坤 <p> GetMapping注解中，produces元素的值必须匹配请求的Accept头部 consumers元素的值必须匹配请求的Content-Type头部 可以在postman中进行测试。 prduces和consumers可以在类级别使用，但是会被方法级别的覆盖,同时还可以在类型前面加上！表示除了该类型
 */
@RestController("/produce-and-consumers")
public class ProduceAndConsumers {
    /**
     * Produce and consumer response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "path1", produces = "text/plain", consumes = "application/json")
    public ResponseEntity<String> produceAndConsumer() {
        return new ResponseEntity<>("我接收到你的请求了，你收到我的响应了吗，弟弟萌？", HttpStatus.OK);
    }

    /**
     * Media类型可以使用MediaType对象来生成，包含了大部分常用的类型
     *
     * @return the response entity
     */
    @GetMapping(value = "path2", produces = "text/plain", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> produceAndConsumerWithMediaType() {
        return new ResponseEntity<>("我接收到你的请求了，你收到我的响应了吗，弟弟萌？", HttpStatus.OK);
    }

    /**
     * Media类型可以使用MediaType对象来生成，包含了大部分常用的类型
     *
     * @return the response entity
     */
    @GetMapping(value = "path3", produces = "text/plain", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> produceAndConsumerWithInverter() {
        return new ResponseEntity<>("我接收到你的请求了，你收到我的响应了吗，弟弟萌？", HttpStatus.OK);
    }

}
