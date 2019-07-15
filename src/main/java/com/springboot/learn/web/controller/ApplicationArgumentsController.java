package com.springboot.learn.web.controller;

import com.springboot.learn.web.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
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

    @Autowired ApplicationArguments applicationArguments;

    @GetMapping("/args")
    public Response<Set<String>> handlerArgs() {
        Response<Set<String>> response = new Response<>();
        //获取不同参数
        response.setData(applicationArguments.getOptionNames());
        return response;
    }

    @GetMapping("/non-args")
    public Response<List<String>> handlerNonArgs() {
        Response<List<String>> response = new Response<>();
        //获取
        response.setData(applicationArguments.getNonOptionArgs());
        return response;
    }
}
