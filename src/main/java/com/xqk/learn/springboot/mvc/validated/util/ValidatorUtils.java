package com.xqk.learn.springboot.mvc.validated.util;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 测试手动校验表单
 *
 * @author 熊乾坤
 * @date 2020-05-24 18:37
 */
public class ValidatorUtils {

    public static Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
