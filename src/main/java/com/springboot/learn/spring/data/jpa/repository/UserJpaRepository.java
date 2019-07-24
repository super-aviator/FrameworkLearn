package com.springboot.learn.spring.data.jpa.repository;

import com.springboot.learn.spring.data.jpa.dto.UserProjectionDTO;
import com.springboot.learn.spring.data.jpa.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The interface User jpa repository.
 *
 * @author Aviator
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {
    /**
     * 查询所有name的用户
     *
     * @param name 字符串类型
     * @return projection ，查询特定字段的值
     */
    List<UserProjectionDTO> findByName(String name);

    /**
     * 使用模糊查询
     *
     * @param name 字符串类型
     * @return 用户列表 list
     */
    @EntityGraph("UserEntity")
    List<User> findByNameContaining(String name);

    /**
     * 使用模糊查询
     *
     * @param name   字符串类型
     * @param tClass 类型信息
     * @return 用户列表 list
     */
    @EntityGraph("UserEntity")
    List<User> findByName(String name, Class<User> tClass);

    /**
     * 按名字删除用户
     *
     * @param username 用户名
     */
    void removeByName(String username);

    /**
     * 测试查询参数为null
     *
     * @param username 用户名
     * @param address  地址
     * @return list
     */
    List<User> findByNameAndAddress(String username, String address);

    /**
     * 测试查询参数为null
     *
     * @param username 用户名
     * @param adds     地址列表
     * @return list
     */
    List<User> findByNameAndAddressIn(String username, String[] adds);

    @Query(value = "SELECT ADDRESS ,COUNT(*) FROM user " +
            " WHERE IF(:name !='', NAME LIKE :name,1=1)" +
            " AND (coalesce (:list,null) is null or EMAIL IN :list)" +
            " GROUP BY ADDRESS"
            , nativeQuery = true)
    List<Object> getUserGroupByGender(@Param("name") String name, @Param("list") List<String> list);
}