package com.xqk.lean.framework.springboot.security.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Security应用Controller
 *
 * @author xiongqiankun
 * @since 2021/9/28 13:48
 */
@Profile("security")
@RestController
@RequestMapping("/security")
public class SecurityApplicationController {
    @GetMapping("/user/info")
    public Object getUserInfo(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
