package com.xqk.learn.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

/**
 * 用户角色配置
 *
 * @author xiongqiankun
 * @since 2021/9/24 9:11
 */
@Configuration
public class UserRoleConfig {
    /**
     * 定义角色的继承关系，ADMIN角色继承USER角色的权限
     *
     * @return RoleHierarchy
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return hierarchy;
    }
}
