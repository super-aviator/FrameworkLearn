package com.xqk.learn.springboot.transaction.dao;

import com.xqk.learn.springboot.transaction.entity.InsideEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 熊乾坤
 * @since 2021-05-06 19:19
 */
public interface InsideDAO extends CrudRepository<InsideEntity,Long> {
}
