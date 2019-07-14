package com.springboot.learn.web.dto;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Aviator
 */
@Getter
@ToString
public class UserProjectionDTO {
    /**
     * 使用Projection时，字段必须为final修饰
     */
    private final String name, email;

    public UserProjectionDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}