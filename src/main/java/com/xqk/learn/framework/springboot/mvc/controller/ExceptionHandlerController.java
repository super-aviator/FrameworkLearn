package com.xqk.learn.framework.springboot.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 注解在Controller层的@ExceptionHandler，那么它会接收并处理由控制器（或其任何子类）中的@RequestMapping方法抛出的异常
 *
 * @author 熊乾坤
 */
@RestController
@RequestMapping("/handlerException")
@Slf4j
public class ExceptionHandlerController {
    @GetMapping("/throwIOException")
    public void throwIoException() throws IOException {
        throw new IOException();
    }

    @GetMapping("/throwFileNotFoundException")
    public void throwFileNotFoundException() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    @GetMapping("/throwArrayIndexOutOfBoundsException")
    public void throwArrayIndexOutOfBoundsException() {
        throw new ArrayIndexOutOfBoundsException();
    }

    /**
     * 返回ResponseEntity类，使用静态的方法，为其指定状态码和响应体
     * 在方法中，可以指定参数为想要捕获的类的父类，Spring会自动传入对应的异常子类型
     * <p>
     * 如果没有给注解任何参数值，那么默认处理的异常类型将是方法参数所声明的那些异常
     * <p>
     * 如果所属的Controller抛出的异常不在@ExceptionHandler的元素列表中，
     * 那么异常就会被@ControllerAdvice注解的类中被@ExceptionHandler注解的方法所处理
     *
     * @param e 捕获的异常
     * @return ResponseEntity
     */
    @ExceptionHandler({IOException.class, FileNotFoundException.class})
    public ResponseEntity<String> handlerException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }


}
