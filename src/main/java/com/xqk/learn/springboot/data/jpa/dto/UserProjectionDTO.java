package com.xqk.learn.springboot.data.jpa.dto;

import lombok.Getter;
import lombok.ToString;


/**
 * The type User projection dto.
 *
 * @author Aviator
 */
@Getter
@ToString
public class UserProjectionDTO {
    /**
     * 使用Projection时，字段必须为final修饰
     */
    private final String name, email;

    /**
     * Instantiates a new User projection dto.
     *
     * @param name  the name
     * @param email the email
     */
    public UserProjectionDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}