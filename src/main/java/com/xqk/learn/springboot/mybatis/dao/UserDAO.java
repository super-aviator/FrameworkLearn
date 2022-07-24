package com.xqk.learn.springboot.mybatis.dao;

import com.xqk.learn.springboot.data.jpa.entity.User;
import com.xqk.learn.springboot.mybatis.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Profile("mybatis")
public class UserDAO {
    private final UserMapper userMapper;

    // public List<UserDTO> findUserByName(String name) {
    // List<User> result = userMapper.selectByCondition(Condition.builder(User.class)
    //                                                           .where(Sqls.custom()
    //                                                                      .andEqualTo("name", name))
    //                                                           .orderBy("name")
    //                                                           .build());
    //User user1=new User();
    //user1.setName(name);
    //List<User> result = userMapper.select(user1);
    // return result.stream()
    //              .map(user->{
    //                  UserDTO userDTO = new UserDTO();
    //                  BeanUtils.copyProperties(user, userDTO);
    //                  return userDTO;
    //              })
    //              .collect(Collectors.toList());


    // }

    public void insertUser(User user) {
        userMapper.insert(user);
    }
}
