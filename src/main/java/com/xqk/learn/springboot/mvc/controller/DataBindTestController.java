package com.xqk.learn.springboot.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 测试SpringBoot数据绑定结果：
 * 1. 如果后台使用List<String>接收数据，前端传入'1,2,3,',后台接收结果为:["1","2","3",""]
 *
 * @author 熊乾坤
 * @since 2019-10-28 14:48
 */
@Slf4j
@RestController
@RequestMapping("/dataBind")
public class DataBindTestController {
    @GetMapping("/list")
    public String getListRequest(@RequestParam List<String> ids) {
        log.info("后台接收到List<String>数据结果为：{}", Arrays.toString(ids.toArray()));
        return Arrays.toString(ids.toArray());
    }
}
