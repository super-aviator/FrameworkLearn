package com.xqk.learn.springboot.junit.springboot.mockmvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author xiongqiankun
 * @since 2022/1/25 19:18
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World";
    }
}
