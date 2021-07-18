package com.xqk.learn.springboot.mybatis.dao;

import com.github.pagehelper.PageHelper;
import com.xqk.learn.springboot.data.jpa.entity.User;
import com.xqk.learn.springboot.mybatis.dto.UserDTO;
import com.xqk.learn.springboot.mybatis.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class UserDAO {
    private final UserMapper userMapper;

    public List<UserDTO> findUserByName(String name) {
        List<User> result = userMapper.selectByCondition(Condition.builder(User.class)
                                                                  .where(Sqls.custom()
                                                                             .andEqualTo("name", name))
                                                                  .orderBy("name")
                                                                  .build());
        //User user1=new User();
        //user1.setName(name);
        //List<User> result = userMapper.select(user1);
        return result.stream()
                     .map(user->{
                         UserDTO userDTO = new UserDTO();
                         BeanUtils.copyProperties(user, userDTO);
                         return userDTO;
                     })
                     .collect(Collectors.toList());


    }
}
