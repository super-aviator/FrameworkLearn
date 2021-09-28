package com.xqk.learn.springboot.security.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 自定义用户名密码数据源
 *
 * @author xiongqiankun
 * @since 2021/9/23 16:40
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityBeanConfig {
    private final DataSource dataSource;

    /**
     * 自定义基于数据库的用户名密码存储方式
     *
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService getUserDetailService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        if (!manager.userExists("xqk")) {
            manager.createUser(User.withUsername("xqk").password("123").roles("ADMIN").build());
        }
        if (!manager.userExists("yxm")) {
            manager.createUser(User.withUsername("yxm").password("123").roles("USER").build());
        }
        return manager;
    }

    /**
     * 获取令牌持久化实现类，使支持令牌持久化
     *
     * @return JdbcTokenRepositoryImpl
     */
    @Bean
    public JdbcTokenRepositoryImpl getTokenRepositoryImpl() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    /**
     * 自定义验证码配置
     *
     * @return Producer
     */
    @Bean
    Producer verifyCode() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "150");
        properties.setProperty("kaptcha.image.height", "50");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
