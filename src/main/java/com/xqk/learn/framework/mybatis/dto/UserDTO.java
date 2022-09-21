package com.xqk.learn.framework.mybatis.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.xqk.learn.framework.springboot.data.jpa.common.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;

    private Long version;

    private String name;

    private String email;

    private String address;

    private Gender gender;

    private Integer score;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}