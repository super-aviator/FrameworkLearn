package com.xqk.learn.springboot.data.elasticsearch.controller;

import com.xqk.learn.springboot.data.elasticsearch.entity.Item;
import com.xqk.learn.springboot.data.elasticsearch.repository.MallItemRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 熊乾坤
 * @date 2020-05-18 20:51
 */
@RestController
@RequestMapping("/es")
public class ElasticsearchController {

    private final MallItemRepository mallItemRepository;

    public ElasticsearchController(MallItemRepository mallItemRepository) {
        this.mallItemRepository = mallItemRepository;
    }

    @PostMapping("/save")
    public void write(@RequestBody Item item) {
        mallItemRepository.save(item);
    }
}
