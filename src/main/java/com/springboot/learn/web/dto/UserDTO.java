package com.springboot.learn.web.dto;

import lombok.*;

/**
 * @author Aviator
 */
@Getter
@ToString
public class UserDTO {
    /**
     * 使用Projection时，字段必须为final修饰
     */
    private final String name, email;

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}