package com.springboot.learn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试重定向和转发的Controller
 * 重定向中客户端会发送两条http请求
 * 转发中客户端只会发送一条请求，服务端发送一条请求
 *
 * alt+鼠标左键可以竖着复制文字，拷贝doc中的文字非常方便
 *
 * @author Aviator
 */
@Controller
public class RedirectAndForwardController {
    @GetMapping("/redirect")
    public String redirectBefore(){
        return "redirect:/redirect-handler";
    }

    @GetMapping("/redirect-handler")
    @ResponseBody
    public String redirect(){
        return "redirect success";
    }

    /**
     * forward跳转的返回值类型没有限制
     * @return
     */
    @GetMapping("/forward")
    public String forward(){
        return "forward:/forward-list";
    }

    @GetMapping("/forward-string")
    @ResponseBody
    public String forwardHandler(){
        return "forward success";
    }

    @GetMapping("/forward-list")
    @ResponseBody
    public List forwardHandlerReturnList(){
        return new ArrayList<>(Arrays.asList("forward","success"));
    }
}
