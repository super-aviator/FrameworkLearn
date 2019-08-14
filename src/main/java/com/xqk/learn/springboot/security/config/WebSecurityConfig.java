package com.xqk.learn.springboot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The type Web security config.
 *
 * @author Aviator
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                //5.x版本的SpringSecurity需要自定义密码解码器
                .passwordEncoder(new MyPasswordEncoder())
                .withUser("xqk")
                .password("123456")
                .roles("USER").and()
                .withUser("yxm")
                .password("654321")
                .roles("ADMIN").and()
                .withUser("cr")
                .password("123321")
                .roles("ADMIN", "USER");

//        auth.jdbcAuthentication()
//                .dataSource();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//         常规的安全认证
//        http.authorizeRequests()
//                .anyRequest().authenticated().and()
//                .formLogin().and()
//                .httpBasic();

//                指定登录地址以及允许所有用户通过表单的方式登录
//                .formLogin().loginPage("/log/in").permitAll();

        http.authorizeRequests()
//                路径为/user开头的url只能被ROLEUSER访问（ROLE）是Spring自动添加的
                .antMatchers("/user/**").hasRole("USER")
//                路径为/time开头的url只能被ROLEADMIN访问（ROLE）是Spring自动添加的
                .antMatchers("/admin/**").hasRole("ADMIN")
//                路径为/path-variable开头的url只能被同时拥有ROLEADMIN和ROLEUSER角色的用户访问（ROLE）是Spring自动添加的,可以通过（and、or控制）
                .antMatchers("/all/**").access("hasRole('ADMIN') or hasRole('USER')")
//                其它任何没有进行匹配的URLs只需要用户认证过即可访问。
                .anyRequest().authenticated().and()
//                关闭CSRF保护，否则POST请求必须带CSRF参数,不带的话请求会报403
                .csrf().disable()
                .formLogin().and()
                .httpBasic();

    }
}
