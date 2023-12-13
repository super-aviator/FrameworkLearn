package com.xqk.learn.framework.springboot.data.jpa.controller;

import com.xqk.learn.framework.springboot.data.jpa.entity.TestColumnEntity;
import com.xqk.learn.framework.springboot.data.jpa.repository.TestColumnRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 熊乾坤
 * @since 2020-01-12 13:03
 */
@RestController
@RequestMapping("/lazy-fetch")
public class LazyFetchTestController {
    private final TestColumnRepository repository;

    public LazyFetchTestController(TestColumnRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/{id}")
    public Map<String, String> getLazyFetchField1(@PathVariable(name = "id") Long id) {
        Optional<TestColumnEntity> result = repository.findById(id);
        Map<String, String> map = new HashMap<>(5);
        result.ifPresent(t -> map.put(t.getDesc(), t.getAddress()));
        return map;
    }

    @RequestMapping("/fall")
    public Map<String, String> getLazyFetchField2() {
        TestColumnEntity testColumnEntity = new TestColumnEntity();
        testColumnEntity.setId(1L);
        testColumnEntity.setName("xqk");
        Map<String, String> map = new HashMap<>(5);
        map.put(testColumnEntity.getDesc(), testColumnEntity.getAddress());
        return map;
    }
}
