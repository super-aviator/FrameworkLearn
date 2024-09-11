package com.xqk.lean.framework.springboot.elasticsearch.component;

import com.xqk.lean.framework.springboot.elasticsearch.lowlevel.service.ElasticsearchSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author 熊乾坤
 * @since 2020-09-12 16:02
 */
@Slf4j
@Component
@Profile("elasticsearch")
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
