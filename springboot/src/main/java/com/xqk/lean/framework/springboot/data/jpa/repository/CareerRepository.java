package com.xqk.lean.framework.springboot.data.jpa.repository;

import com.xqk.lean.framework.springboot.data.jpa.entity.CareerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Career Repository类
 *
 * @author 熊乾坤
 * @since 2020-03-01 13:17
 */
@Repository
public interface CareerRepository extends JpaRepository<CareerEntity, Long> {
}
