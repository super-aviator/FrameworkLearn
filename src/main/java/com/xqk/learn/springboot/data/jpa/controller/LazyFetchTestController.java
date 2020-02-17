package com.xqk.learn.springboot.data.jpa.controller;

import com.xqk.learn.springboot.data.jpa.entity.LazyFetchTestEntity;
import com.xqk.learn.springboot.data.jpa.repository.LazyFetchTestRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 熊乾坤
 * @date 2020-01-12 13:03
 */
@RestController
@RequestMapping("/lazy-fetch")
public class LazyFetchTestController {
    private final LazyFetchTestRepository repository;

    public LazyFetchTestController(LazyFetchTestRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/{id}")
    public Map<String, String> getLazyFetchField1(@PathVariable(name = "id") Long id) {
        Optional<LazyFetchTestEntity> result = repository.findById(id);
        Map<String, String> map = new HashMap<>(5);
        result.ifPresent(t -> map.put(t.getDesc(), t.getAddress()));
        return map;
    }

    @RequestMapping("/fall")
    public Map<String, String> getLazyFetchField2() {
        LazyFetchTestEntity lazyFetchTestEntity = new LazyFetchTestEntity();
        lazyFetchTestEntity.setId(1L);
        lazyFetchTestEntity.setName("xqk");
        Map<String, String> map = new HashMap<>(5);
        map.put(lazyFetchTestEntity.getDesc(), lazyFetchTestEntity.getAddress());
        return map;
    }
}
