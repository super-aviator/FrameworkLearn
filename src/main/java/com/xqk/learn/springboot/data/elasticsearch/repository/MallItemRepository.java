package com.xqk.learn.springboot.data.elasticsearch.repository;

import com.xqk.learn.springboot.data.elasticsearch.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 熊乾坤
 * @date 2020-05-18 20:50
 */
@Repository
public interface MallItemRepository extends ElasticsearchCrudRepository<Item, Long> {
}
