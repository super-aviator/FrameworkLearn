package com.xqk.learn.springboot.security.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证不同的用户角色是否可以登录不用的路径
 *
 * @author Aviator
 */
@Profile("security")
@RestController
@RequestMapping("/security")
public class UserSecurityController {
    /**
     * DatasourceUser path 1 string.
     *
     * @return the string
     */
    @GetMapping("/user/path1")
    public String userPath1() {
        return "恭喜你登录成功了";
    }

    /**
     * DatasourceUser path 2 string.
     *
     * @return the string
     */
    @GetMapping("/user/path2")
    public String userPath2() {
        return "恭喜你登录成功了";
    }

    /**
     * Admin path 1 string.
     *
     * @return the string
     */
    @GetMapping("/admin/path1")
    public String adminPath1() {
        return "恭喜你登录成功了";
    }

    /**
     * Admin path 2 string.
     *
     * @return the string
     */
    @GetMapping("/admin/path2")
    public String adminPath2() {
        return "恭喜你登录成功了";
    }

    /**
     * All path 1 string.
     *
     * @return the string
     */
    @GetMapping("/all/path1")
    public String allPath1() {
        return "恭喜你登录成功了";
    }

    /**
     * All path 2 string.
     *
     * @return the string
     */
    @GetMapping("/all/path2")
    public String allPath2() {
        return "恭喜你登录成功了";
    }
}
