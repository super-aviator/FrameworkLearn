package com.xqk.learn.framework.springboot.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 用于基础测试，包含一些功能性的接口
 *
 * @author 熊乾坤
 * @since 2020-12-27 17:00
 */
@RestController
@RequestMapping("/basic")
public class BaseController {
    /**
     * 项目最基础的一个接口，用于测试服务是否正常
     *
     * @return Hello World
     */
    @GetMapping("/hello")
    public String getMessage() {
        return "Hello World";
    }

    /**
     * 一个带延迟功能的接口，延迟时间为随机的
     *
     * @return Hello World
     */
    @GetMapping("/delayWithRandom")
    public String delayWithRandom() throws InterruptedException {
        Thread.sleep(new Random().nextInt(60) * 1000);
        return "Hello World";
    }

    /**
     * 一个带延迟功能的接口
     *
     * @return Hello World
     */
    @GetMapping("/delayWithParam/{milliSeconds}")
    public String delayWithParam(@PathVariable Long milliSeconds) throws InterruptedException {
        Thread.sleep(milliSeconds);
        return "Hello World";
    }
}
