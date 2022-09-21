package com.xqk.learn.framework.springboot.data.jpa.repository;

import com.xqk.learn.framework.springboot.data.jpa.entity.TestColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 熊乾坤
 * @date 2020-01-12 12:57
 */
public interface TestColumnRepository extends JpaRepository<TestColumnEntity, Long> {
}
