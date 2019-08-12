package com.springboot.learn.data.jpa.repository;

import com.springboot.learn.data.jpa.dto.UserProjectionDTO;
import com.springboot.learn.data.jpa.dto.UserScoreDTO;
import com.springboot.learn.data.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 通过姓名查询用户列表
     *
     * @param name   字符串类型
     * @param tClass 类型信息
     * @return 用户列表 list
     */
    @EntityGraph("UserEntity")
    List<User> findByName(String name, Class<User> tClass);

    /**
     * 通过姓名查询用户列表
     *
     * @param name     字符串类型
     * @param pageable 分页信息
     * @return 用户列表 list
     */
    Page<User> findByName(String name, Pageable pageable);

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
     * @return list list
     */
    List<User> findByNameAndAddress(String username, String address);

    /**
     * 测试查询参数为null
     *
     * @param username 用户名
     * @param adds     地址列表
     * @return list list
     */
    List<User> findByNameAndAddressIn(String username, String[] adds);

    /**
     * Gets user group by gender.
     *
     * @param name the name
     * @param list the list
     * @return the user group by gender
     */
    @Query(value = "SELECT t.address ,COUNT(t) " +
            "FROM User AS t " +
            " WHERE t.name LIKE :name" +
            " AND (coalesce (:list,null) is null or t.email IN :list)" +
            " GROUP BY t.address")
    List<Object> getUserGroupByGender(@Param("name") String name, @Param("list") List<String> list);

    /**
     * 测试avg函数
     *
     * @param name     name
     * @param pageable 分页信息
     * @return list
     */
    @Query(value = "SELECT new com.springboot.learn.data.jpa.dto.UserScoreDTO(t.name,t.email,AVG(t.score)) " +
            "FROM User AS t " +
            "GROUP BY t.name,t.email " +
            "HAVING  t.name = ?1 ")
    List<UserScoreDTO> getUserScoreAvg(String name, Pageable pageable);
}