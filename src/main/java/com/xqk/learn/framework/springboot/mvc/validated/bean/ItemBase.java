package com.xqk.learn.framework.springboot.mvc.validated.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 熊乾坤
 * @since 2020-05-24 22:03
 */
@Data
public class ItemBase {
    @NotNull(message = "id不能为空")
    private Long id;
}
