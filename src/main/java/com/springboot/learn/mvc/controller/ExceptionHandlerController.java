package com.springboot.learn.mvc.controller;

import com.springboot.learn.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 熊乾坤
 */
@RestController
@RequestMapping("/handlerException")
@Slf4j
public class ExceptionHandlerController {
    @GetMapping("/throwException")
    public void throwException() {
//        throw new IOException();
//        throw new RuntimeException();
    }

    @ExceptionHandler(RuntimeException.class)
    public Response handlerException() {
        return new Response(500);
    }
}
