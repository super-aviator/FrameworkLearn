package com.xqk.learn.springboot.transaction.dao;

import com.xqk.learn.springboot.transaction.entity.OutsideEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 熊乾坤
 * @since 2021-05-06 19:20
 */
public interface OutsideDAO extends CrudRepository<OutsideEntity,Long> {
}
