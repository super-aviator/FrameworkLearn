// package com.xqk.learn.framework.springboot.mvc.controller;
//
// import com.xqk.learn.framework.common.ResponseMessage;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.web.bind.annotation.*;
//
// import java.util.Arrays;
// import java.util.List;
//
// /**
//  * 测试SpringBoot数据绑定结果：
//  * 1. 如果后台使用List<String>接收数据，前端传入'ids=1,2,3,',后台接收结果为：["1","2","3",""]
//  * 2. 如果后台使用List<String>接收数据，前端传入'ids='，后台接收结果为：[]
//  * 3. 如果后台的路径参数/{id}，前端传入'/',后台无法接收到结果，提示路径不存在
//  *
//  * @author 熊乾坤
//  * @since 2019-10-28 14:48
//  */
// @Slf4j
// @RestController
// @RequestMapping("/data-bind")
// public class DataBindTestController {
//     @GetMapping("/list")
//     public String getListRequest(@RequestParam List<String> ids) {
//         log.info("后台接收到List<String>数据结果为：{}", Arrays.toString(ids.toArray()));
//         return Arrays.toString(ids.toArray());
//     }
//
//     @GetMapping("/path-variable/{id}")
//     public Long getPathVariable(@PathVariable Long id) {
//         log.info("后台接收到id数据结果为：{}", id);
//         return id;
//     }
//
//     @GetMapping("/path-variable/{id1}/{id2}")
//     public ResponseMessage getPathVariables(@PathVariable Long id1, @PathVariable List<Long> id2, @RequestParam("type") String type) {
//         log.info("后台接收到id1数据结果为：{}", id1);
//         log.info("后台接收到id2数据结果为：{}", id2);
//         log.info("后台接收到type数据结果为：{}", type);
//
//         JSONObject jo = new JSONObject();
//         jo.put("id1", id1);
//         jo.put("id2", id2);
//         jo.put("type", type);
//         return ResponseMessage.ok(jo);
//     }
// }
