package com.springboot.learn.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证不同的用户角色是否可以登录不用的路径
 *
 * @author Aviator
 */
@RestController
public class UserSecurityController {
    @GetMapping("/user/path1")
    public String userPath1() {
        return "恭喜你登录成功了";
    }

    @GetMapping("/user/path2")
    public String userPath2() {
        return "恭喜你登录成功了";
    }

    @GetMapping("/admin/path1")
    public String adminPath1() {
        return "恭喜你登录成功了";
    }

    @GetMapping("/admin/path2")
    public String adminPath2() {
        return "恭喜你登录成功了";
    }

    @GetMapping("/all/path1")
    public String allPath1() {
        return "恭喜你登录成功了";
    }

    @GetMapping("/all/path2")
    public String allPath2() {
        return "恭喜你登录成功了";
    }
}
