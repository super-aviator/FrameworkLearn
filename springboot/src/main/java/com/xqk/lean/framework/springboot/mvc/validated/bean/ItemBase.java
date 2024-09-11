package com.xqk.lean.framework.springboot.mvc.validated.bean;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * @author 熊乾坤
 * @since 2020-05-24 22:03
 */
@Data
public class ItemBase {
    @NotNull(message = "id不能为空")
    private Long id;
}
