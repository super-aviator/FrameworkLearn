package com.xqk.lean.framework.springboot.mvc.controller;

import com.xqk.lean.framework.springboot.common.ResponseMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 尝试将版本号集成到请求url中，使用版本号对url进行控制
 *
 * @author 熊乾坤
 * @since 2019-11-21 14:14
 */
@RestController
@RequestMapping("/v1.0")
public class PathWithVersionController {
    @RequestMapping("/getResource")
    public ResponseMessage<String> getVersionResource(String str) {
        return ResponseMessage.ok("访问成功啦");
    }
}
