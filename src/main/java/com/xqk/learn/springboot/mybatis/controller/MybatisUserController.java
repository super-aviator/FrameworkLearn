package com.xqk.learn.springboot.mybatis.controller;

import com.xqk.learn.springboot.mybatis.dao.UserDAO;
import com.xqk.learn.springboot.mybatis.dto.UserDTO;
import com.xqk.learn.springboot.mybatis.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mybatis")
@AllArgsConstructor
public class MybatisUserController {
    private final UserService userService;
    private final UserDAO userDAO;


    @GetMapping("/findAllUser")
    public List<UserDTO> findAllUser() {
        return userService.getUserList();
    }

    @GetMapping("/findUserByName")
    public List<UserDTO> findUserByName(@RequestParam String name) {
        return userDAO.findUserByName(name);
    }
}
