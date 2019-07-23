package com.springboot.learn.spring.mvc.controller;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 熊乾坤
 */

@RestController
@RequestMapping("/time")
@Log
public class TimeConvertController {
    @GetMapping("/receive-string")
    public void timeWithConvertString(@RequestParam("exampleTime") String exampleTime) {
        log.info(exampleTime);
    }
}
