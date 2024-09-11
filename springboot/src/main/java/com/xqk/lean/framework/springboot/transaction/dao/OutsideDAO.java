package com.xqk.lean.framework.springboot.transaction.dao;


import com.xqk.lean.framework.springboot.transaction.entity.OutsideEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 熊乾坤
 * @since 2021-05-06 19:20
 */
@Repository
public interface OutsideDAO extends CrudRepository<OutsideEntity,Long> {
}
