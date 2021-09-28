package com.xqk.learn.springboot.mvc.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 注解@ControllerAdvice的类可以拥有@ExceptionHandler、@InitBinder或@ModelAttribute注解的方法，
 * 并且这些方法会被应用至控制器类层次??的所有@RequestMapping方法上。
 * <p>
 * 如果需要返回JSON格式的字符串，可以使用@RestControllerAdvice
 * <p>
 * 可以再@ExceptionHandler下加入@ResponseStatus注解，定义返回的响应的状态码
 *
 * @author 熊乾坤
 */
@ControllerAdvice
public class ControllerAdviceController {

    /**
     * 注解在@ControllerAdvice上的@ExceptionHandler会处理所有Controller抛出的异常
     * <p>
     * 如果没有给注解任何参数值，那么默认处理的异常类型将是方法参数所声明的那些异常
     *
     * @param e 捕获的Exception
     * @return ResponseEntity
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handlerException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
}
