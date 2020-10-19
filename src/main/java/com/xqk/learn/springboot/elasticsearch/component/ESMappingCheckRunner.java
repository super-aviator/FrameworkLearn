package com.xqk.learn.springboot.elasticsearch.component;

import com.xqk.learn.springboot.elasticsearch.lowlevel.service.ElasticsearchSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 熊乾坤
 * @date 2020-09-12 16:02
 */
@Slf4j
@Component
public class ESMappingCheckRunner implements CommandLineRunner {
    private final ElasticsearchSearchService elasticsearchSearchService;

    public ESMappingCheckRunner(ElasticsearchSearchService elasticsearchSearchService) {
        this.elasticsearchSearchService = elasticsearchSearchService;
    }

    @Override
    public void run(String... args) {
        try {
            if (elasticsearchSearchService.indexExists("test")) {
                log.info("索引[{}]存在", "test");
            } else {
                log.error("索引：[{}]不存在，将创建索引", "test");
                elasticsearchSearchService.createIndex("test", "es/mapping/mapping-test.json");
            }
        } catch (Exception e) {
            log.error("检查索引是否存在时异常", e);
        }
    }
}
