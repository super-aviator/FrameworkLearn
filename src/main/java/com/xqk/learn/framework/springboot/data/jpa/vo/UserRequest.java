package com.xqk.learn.framework.springboot.data.jpa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 熊乾坤
 * @since 2019-11-24 13:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private Long scoreSum;
}