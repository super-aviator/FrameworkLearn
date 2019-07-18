package com.springboot.learn.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The type User dto.
 *
 * @author 熊乾坤
 */
@Data
public class UserDTO {

    private Long id;

    private Long version;

    @NotNull
    @NotBlank(message = "姓名不能为空")
    private String name;

    @Pattern(regexp = "[0-9]+@(qq|163|gmail).com", message = "邮箱不匹配")
    private String email;

    private String address;
}
