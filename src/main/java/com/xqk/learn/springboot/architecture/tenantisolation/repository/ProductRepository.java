package com.xqk.learn.springboot.architecture.tenantisolation.repository;

import com.xqk.learn.springboot.architecture.tenantisolation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 熊乾坤
 * @date 2020-03-30 10:02
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}
