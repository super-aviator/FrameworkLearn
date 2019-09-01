package com.xqk.learn.springboot.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 需要自定义密码解码器，这是因为Spring boot 2.0.3引用的security依赖是spring security 5.X版本，
 * 此版本需要提供一个PasswordEncoder的实例，否则后台会吗报错误：
 * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
 *
 * @author Aviator
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
