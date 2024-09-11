package com.xqk.lean.framework.springboot.transaction.dao;


import com.xqk.lean.framework.springboot.transaction.entity.InsideEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 熊乾坤
 * @since 2021-05-06 19:19
 */
@Repository
public interface InsideDAO extends CrudRepository<InsideEntity,Long> {
}
