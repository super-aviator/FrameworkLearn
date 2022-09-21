package com.xqk.learn.framework.spring.util.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 熊乾坤
 * @date 2020-03-10 20:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Cla1Derived extends Cla1 {
    private Long id;
}
