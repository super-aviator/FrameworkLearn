package com.xqk.learn.springboot.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 熊乾坤
 * @since 2020-12-27 17:00
 */
@RestController
@RequestMapping("/base")
public class BaseController {
    /**
     * 项目最基础的一个接口，用于测试服务是否正常
     *
     * @return Hello World
     */
    @GetMapping("/get")
    public String getMessage() {
        return "Hello World";
    }

    /**
     * 一个带延迟功能的接口
     *
     * @return Hello World
     */
    @GetMapping("/get/sleep/{milliSeconds}")
    public String getMessageAfterSleepMilliSeconds(@PathVariable Long milliSeconds) throws InterruptedException {
        Thread.sleep(milliSeconds);
        return "Hello World";
    }
}
