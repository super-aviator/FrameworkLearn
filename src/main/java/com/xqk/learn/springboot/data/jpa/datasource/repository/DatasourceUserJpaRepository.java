package com.xqk.learn.springboot.data.jpa.datasource.repository;

import com.xqk.learn.springboot.data.jpa.datasource.entity.DatasourceUser;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 熊乾坤
 * @since 2020-01-06 13:54
 */
@Repository
@Profile("datasource")
public interface DatasourceUserJpaRepository extends JpaRepository<DatasourceUser, Long> {
}
