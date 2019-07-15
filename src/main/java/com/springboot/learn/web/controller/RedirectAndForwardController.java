package com.springboot.learn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试重定向和转发的Controller
 * 重定向中客户端会向服务端发送两条http请求，服务端发送两条响应
 * 转发中客户端只会发送一条请求，服务端发送一条响应
 *
 * alt+鼠标左键可以竖着复制文字，拷贝doc中的文字非常方便，直接用滚轮也可以哈
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
     * forward跳转前的返回值与跳转后的返回值可以不相同,没有限制
     * @return 字符串
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
