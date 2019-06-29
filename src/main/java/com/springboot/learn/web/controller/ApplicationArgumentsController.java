package com.springboot.learn.web.controller;

import com.springboot.learn.web.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用ApplicationArguments接口获取main方法中的args参数
 *
 * @author Aviator
 */
@RestController
public class ApplicationArgumentsController {

    @Autowired ApplicationArguments applicationArguments;

    @GetMapping("/args")
    public Response handlerArgs(){
        Response response=new Response();
        response.setData(applicationArguments.getOptionNames());
        return response;
    }

    @GetMapping("/non-args")
    public Response handlerNonArgs(){
        Response response=new Response();
        response.setData(applicationArguments.getNonOptionArgs());
        return response;
    }
}
