package com.xqk.learn.framework.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.xqk.learn.framework.mybatis.dto.UserDTO;
import com.xqk.learn.framework.mybatis.mapper.UserMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("mybatis")
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserDTO> getUserList() {
        PageHelper.startPage(2,1);
        return userMapper.findAllUser();
    }
}
