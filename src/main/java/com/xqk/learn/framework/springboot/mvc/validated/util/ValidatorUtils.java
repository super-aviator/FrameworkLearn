package com.xqk.learn.framework.springboot.mvc.validated.util;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

/**
 * 测试手动校验表单
 *
 * @author 熊乾坤
 * @since 2020-05-24 18:37
 */
public class ValidatorUtils {

    public static Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
