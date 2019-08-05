package com.springboot.learn.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 熊乾坤
 */
@RestController
@Slf4j
@RequestMapping("/pathVariable")
public class PathVariableController {
    @GetMapping("/{var}")
    public void test1(@PathVariable String var) {
        log.info(var);
    }

    @GetMapping("/{first:[a-z]+}{second:[0-9]+}.{three:[a-z]+}")
    public String test2(@PathVariable String first, @PathVariable String second, @PathVariable String three) {
        log.info(first + "**" + second + "**" + three);
        log.info("8888888");
        return "接收到了";
    }

    @GetMapping("/num/{id:\\d+}")
    public String test3(@PathVariable String id) {
        log.info(id);
        return "接收到了";
    }

    @GetMapping("/num/*/{id:\\d+}/get")
    public String test4(@PathVariable String id) {
        log.info(id);
        return "接收到了";
    }

    /**
     * 就版本中，/file也会默认映射为/file.*,所以例如file.pdf,file.xml也会被映射，这可能会导致黑客攻击
     * 新版本中已经取消了该功能
     *
     * @return 提示消息
     */
    @GetMapping("/file")
    public String test5() {
        return "接收到了";
    }
}
