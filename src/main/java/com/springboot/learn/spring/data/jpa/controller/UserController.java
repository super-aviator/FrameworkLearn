package com.springboot.learn.spring.data.jpa.controller;

import com.springboot.learn.spring.data.jpa.dto.UserProjectionDTO;
import com.springboot.learn.spring.data.jpa.entity.User;
import com.springboot.learn.spring.data.jpa.repository.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * The type User controller.
 *
 * @author Aviator
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    /**
     * The User repository.
     */
    @Autowired
    UserJpaRepository userRepository;

    /**
     * 使用Projections获取部分属性
     *
     * @return list all user
     */
    @GetMapping("/all")
    public List getAllUser() {
        return userRepository.findByName("熊乾坤");
    }

    /**
     * 模糊查询，containing会在Mysql的查询语句中LIKE的参数前后插入%
     *
     * @return list containing user
     */
    @GetMapping(value = "/containing")
    public List getContainingUser() {
        return userRepository.findByNameContaining("熊");
    }

    /**
     * 动态的Projection,第二个参数即为想要返回的结果的类型
     *
     * @return list dynamic user
     */
    @GetMapping("/dynamic")
    public List getDynamicUser() {
        return userRepository.findByName("熊乾坤", User.class);
    }

    /**
     * 删除需要在Service层使用@Transactional注解，没有Service层就写在Controller层算了吧
     *
     * @param username 用户名
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/delete")
    public void deleteUser(@RequestParam(value = "username") String username) {
        userRepository.removeByName(username);
    }

    /**
     * Post user string.
     *
     * @param userDTO the user dto
     * @param errors  the errors
     * @return the string
     */
    @PostMapping("/add")
    public String postUser(@Validated UserProjectionDTO userDTO, Errors errors) {
        if (errors.hasErrors()) {
            log.info(userDTO.toString());
            return Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
        } else {
            log.info(userDTO.toString());
            return "验证通过";
        }
    }

    @GetMapping("/param-with-null")
    public List<User> getUsers() {
        return userRepository.findByNameAndAddress("熊乾坤", null);
    }

    @GetMapping("/get-user-in")
    public List<User> getUsersIn() {
        return userRepository.findByNameAndAddressIn("熊乾坤", new String[]{"湖北宜昌", "湖北武汉"});
    }
}