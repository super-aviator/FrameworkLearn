package com.xqk.learn.framework.mybatis.controller;

import com.xqk.learn.framework.springboot.data.jpa.entity.User;
import com.xqk.learn.framework.mybatis.dao.UserDAO;
import com.xqk.learn.framework.mybatis.dto.UserDTO;
import com.xqk.learn.framework.mybatis.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mybatis")
@AllArgsConstructor
@Profile("mybatis")
public class MybatisUserController {
    private final UserService userService;
    private final UserDAO userDAO;


    @GetMapping("/findAllUser")
    public List<UserDTO> findAllUser() {
        return userService.getUserList();
    }

    @GetMapping("/findUserByName")
    public List<UserDTO> findUserByName(@RequestParam String name) {
        // return userDAO.findUserByName(name);
        return null;
    }

    @PostMapping("/insertUser")
    public User insertUser() {
        var user = new User();
        user.setName("test1");
        user.setAddress("test1");
        userDAO.insertUser(user);
        log.info("id:{}", user);
        return user;
    }
}
