package com.xqk.lean.framework.springboot.mybatis.dto;

import com.xqk.lean.framework.springboot.data.jpa.common.Gender;
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

    private Date birthday;
}