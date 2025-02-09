package com.xqk.lean.framework.shardingsphere.controller;

import com.xqk.lean.framework.shardingsphere.mapper.ShardingSphereTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller
 *
 * @author qiankun.xiong
 * @version 1.0.0
 * @since 2025/2/6 15:07
 */
@RestController
@RequestMapping("/shardingsphere")
public class ShardingSphereController {
    @Autowired
    private ShardingSphereTableMapper shardingSphereTableMapper;

    @GetMapping("/selectName")
    public String selectName(@RequestParam("id") Long id) {
        return shardingSphereTableMapper.selectNameById(id);
    }

    @GetMapping("/selectName2")
    public String selectName2(@RequestParam("id") Long id) {
        return shardingSphereTableMapper.selectNameById2(id);
    }

    @GetMapping("/insert")
    public long selectName(@RequestParam("name") String name) {
        return shardingSphereTableMapper.insert(name);
    }
}
