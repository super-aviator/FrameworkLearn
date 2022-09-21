package com.xqk.learn.framework.mybatis.mapper;

import com.xqk.learn.framework.springboot.data.jpa.entity.User;
import com.xqk.learn.framework.mybatis.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;

import java.util.List;

@Mapper
public interface UserMapper extends SelectMapper<User>, SelectByConditionMapper<User>, InsertMapper<User>, SelectByExampleMapper<User>, BaseSelectMapper<User>,
        BaseUpdateMapper<User> {
    @Select("SELECT * FROM user")
    List<UserDTO> findAllUser();
}
