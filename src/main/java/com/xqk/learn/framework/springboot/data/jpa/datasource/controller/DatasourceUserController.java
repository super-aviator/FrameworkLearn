package com.xqk.learn.framework.springboot.data.jpa.datasource.controller;

import com.xqk.learn.framework.springboot.data.jpa.datasource.entity.DatasourceUser;
import com.xqk.learn.framework.springboot.data.jpa.datasource.repository.DatasourceUserJpaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 熊乾坤
 * @since 2020-01-06 13:56
 */
@Profile("datasource")
@RestController
@RequestMapping("/datasource")
public class DatasourceUserController {
    private final DatasourceUserJpaRepository datasourceUserJpaRepository;

    public DatasourceUserController(DatasourceUserJpaRepository datasourceUserJpaRepository) {
        this.datasourceUserJpaRepository = datasourceUserJpaRepository;
    }

    @GetMapping("/findAll")
    public List<DatasourceUser> findAll() {
        return datasourceUserJpaRepository.findAll();
    }

    @PostMapping("/save")
    public DatasourceUser save(@RequestBody DatasourceUser datasourceUser) {
        return datasourceUserJpaRepository.save(datasourceUser);
    }
}
