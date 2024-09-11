package com.xqk.lean.framework.springboot.data.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * The type DatasourceUser dto.
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
