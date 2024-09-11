package com.xqk.lean.framework.springboot.core.annotation;


import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * AnnotationCustomer
 * 自定义注解，达到定制化注解的效果
 *
 * @author xiongqiankun
 * @since 2022/3/18 14:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Inherited
@Transactional(rollbackFor = Exception.class)
public @interface CustomTransactional {
}
