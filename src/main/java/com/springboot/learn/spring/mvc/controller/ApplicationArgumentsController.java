package com.springboot.learn.spring.mvc.controller;

import com.springboot.learn.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 使用ApplicationArguments接口获取main方法中的args参数
 *
 * @author Aviator
 */
@RestController
public class ApplicationArgumentsController {

    /**
     * The Application arguments.
     */
    @Autowired
    ApplicationArguments applicationArguments;

    /**
     * 获取--开头的参数，例如--server.port
     *
     * @return 服务器响应 response
     */
    @GetMapping("/args")
    public Response<Set<String>> handlerArgs() {
        Response<Set<String>> response = new Response<>();
        response.setData(applicationArguments.getOptionNames());
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    /**
     * 获取args参数
     *
     * @return 服务器响应 response
     */
    @GetMapping("/non-args")
    public Response<List<String>> handlerNonArgs() {
        Response<List<String>> response = new Response<>();
        response.setData(applicationArguments.getNonOptionArgs());
        response.setCode(HttpStatus.OK.value());
        return response;
    }
}
