package com.xqk.learn.springboot.data.jpa.repository;

import com.xqk.learn.springboot.data.jpa.entity.LazyFetchTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 熊乾坤
 * @date 2020-01-12 12:57
 */
public interface LazyFetchTestRepository extends JpaRepository<LazyFetchTestEntity, Long> {

}
