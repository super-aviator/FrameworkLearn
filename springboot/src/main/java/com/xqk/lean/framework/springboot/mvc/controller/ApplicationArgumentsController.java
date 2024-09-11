package com.xqk.lean.framework.springboot.mvc.controller;

import com.xqk.lean.framework.springboot.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 使用ApplicationArguments接口获取main方法中的args参数
 *
 * @author Aviator
 */
@RestController
@RequestMapping("/applicationArguments")
public class ApplicationArgumentsController {

    /**
     * The Application arguments.
     */
    private final
    ApplicationArguments applicationArguments;

    @Autowired
    public ApplicationArgumentsController(ApplicationArguments applicationArguments) {
        this.applicationArguments = applicationArguments;
    }

    /**
     * 获取--开头的参数，例如--server.port
     *
     * @return 服务器响应 response
     */
    @GetMapping("/optionArgs")
    public ResponseMessage<Set<String>> handlerArgs() {
        return ResponseMessage.generic(HttpStatus.OK.value(), applicationArguments.getOptionNames());
    }

    /**
     * 获取args参数
     *
     * @return 服务器响应 response
     */
    @GetMapping("/nonOptionArgs")
    public ResponseMessage<List<String>> handlerNonArgs() {
        return ResponseMessage.generic(HttpStatus.OK.value(), applicationArguments.getNonOptionArgs());
    }
}
