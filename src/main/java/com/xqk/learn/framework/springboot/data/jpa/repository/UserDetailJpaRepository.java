package com.xqk.learn.framework.springboot.data.jpa.repository;

import com.xqk.learn.framework.springboot.data.jpa.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 熊乾坤
 * @date 2020-03-14 15:05
 */
@Repository
public interface UserDetailJpaRepository extends JpaRepository<UserDetail, Long> {
}
