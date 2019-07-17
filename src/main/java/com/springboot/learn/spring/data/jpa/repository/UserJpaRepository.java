package com.springboot.learn.spring.data.jpa.repository;

import com.springboot.learn.spring.data.jpa.dto.UserProjectionDTO;
import com.springboot.learn.spring.data.jpa.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Aviator
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {
    /**
     * 查询所有name的用户
     * @param name 字符串类型
     * @return projection，查询特定字段的值
     */
    List<UserProjectionDTO> findByName(String name);

    /**
     * 使用模糊查询
     * @param name 字符串类型
     * @return 用户列表
     */
    @EntityGraph("UserEntity")
    List<User> findByNameContaining(String name);

    /**
     * 使用模糊查询
     * @param name 字符串类型
     * @param tClass 类型信息
     * @return 用户列表
     */
    @EntityGraph("UserEntity")
    List<User> findByName(String name, Class<User> tClass);

    /**
     * 按名字删除用户
     * @param username 用户名
     */
    void removeByName(String username);
}