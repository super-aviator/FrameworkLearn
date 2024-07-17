package com.xqk.learn.framework.springboot.data.elasticsearch.repository;

import com.xqk.learn.framework.springboot.data.elasticsearch.entity.Item;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 熊乾坤
 * @since 2020-05-18 20:50
 */
@Repository
@Profile("elasticsearch")
public interface MallItemRepository extends ElasticsearchRepository<Item, Long> {
}
