package com.xqk.learn.springboot.data.jpa.controller;

import com.xqk.learn.springboot.data.jpa.dto.UserProjectionDTO;
import com.xqk.learn.springboot.data.jpa.entity.User;
import com.xqk.learn.springboot.data.jpa.repository.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The type DatasourceUser controller.
 *
 * @author Aviator
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    /**
     * The DatasourceUser repository.
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
     * 对结果集进行分页
     *
     * @param page 当前页，从0开始
     * @param size 每页显示的大小
     * @return 分页结果
     */
    @GetMapping("/allWithPage")
    public Page<User> getAllUserWithPage(int page, int size) {
        return userRepository.findByName("熊乾坤", PageRequest.of(page, size, Sort.Direction.DESC, "id"));
    }

    /**
     * 模糊查询，containing会在Mysql的查询语句中LIKE的参数前后插入%
     *
     * @return list containing user
     */
    @GetMapping("/containing")
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
     * 删除需要在Service层使用@Transactional注解，由于没有Service层，就写在Controller层算了吧
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

    /**
     * 当查询参数为Null时，会查询对应该字段为值为null的数据。
     *
     * @return the users
     */
    @GetMapping("/paramWithNull")
    public List<User> getUsers() {
        return userRepository.findByNameAndAddress("熊乾坤", null);
    }

    /**
     * 使用In关键字，查找某个范围的数据
     *
     * @return the users in
     */
    @GetMapping("/getUserIn")
    public List<User> getUsersIn() {
        return userRepository.findByNameAndAddressIn("熊乾坤", new String[]{"湖北宜昌", "湖北武汉"});
    }

    /**
     * 使用GroupBy关键字对结果进行分组
     *
     * @param name  the name
     * @param email the email
     * @return the user group by
     */
    @GetMapping("/groupBy")
    public List<Object> getUserGroupBy(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "email", required = false) String email) {
        List<String> list = email == null ? null : Arrays.asList(email.split(","));

        return userRepository.getUserGroupByGender(name, list);
    }

    @GetMapping("/DynamicSql")
    public List<User> getUserByDynamicSql(String name) {
        return userRepository.getUserWithDynamicSql(name);
    }

    @CrossOrigin(origins = {"http://123.123.23.1:9000", "null"})
    @GetMapping("/DynamicSqlWithArgsArr")
    public List<User> getUserByDynamicSqlWithArgsArr(String[] name) {
        return userRepository.getUserWithDynamicSql(Objects.isNull(name) ? null : Arrays.asList(name));
    }
}