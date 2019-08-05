package com.springboot.learn.mvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 注解@ControllerAdvice的类可以拥有@ExceptionHandler、@InitBinder或@ModelAttribute注解的方法，
 * 并且这些方法会被应用至控制器类层次??的所有@RequestMapping方法上。
 *
 * @author 熊乾坤
 */
@ControllerAdvice
public class ControllerAdviceController {

}
