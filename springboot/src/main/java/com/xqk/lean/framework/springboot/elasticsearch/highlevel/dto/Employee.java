package com.xqk.lean.framework.springboot.elasticsearch.highlevel.dto;

import lombok.Data;

/**
 * @author 熊乾坤
 * @since 2020-06-28 20:37
 */
@Data
public class Employee {
    private String id;
    private String title;
    private Integer age;
    private String about;
    private String firstName;
    private String lastName;
    private String gender;
    private String hobby;
}
