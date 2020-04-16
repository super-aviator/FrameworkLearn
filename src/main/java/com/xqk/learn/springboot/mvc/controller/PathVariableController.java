package com.xqk.learn.springboot.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 路径参数测试
 * 1. 对于路径参数/{var}和路径参数/var/{var}，相互之间不会干扰
 * 2. 对于路径参数/{var}，请求路径"/ "会后端无法识别
 *
 *
 * @author 熊乾坤
 */
@RestController
@Slf4j
@RequestMapping("/pathVariable")
public class PathVariableController {
    /**
     * Test 1.
     *
     * @param var the var
     */
    @DeleteMapping("/{var}")
    public void test1(@PathVariable String var) {
        log.info(var);
    }

    /**
     * @param var 参数
     */
    @DeleteMapping("/var/{var}")
    public void test11(@PathVariable String var) {
        log.info(var);
    }

    /**
     * Test 2 string.
     *
     * @param first  the first
     * @param second the second
     * @param three  the three
     * @return the string
     */
    @GetMapping("/{first:[a-z]+}{second:[0-9]+}.{three:[a-z]+}")
    public String test2(@PathVariable String first, @PathVariable String second, @PathVariable String three) {
        log.info(first + "**" + second + "**" + three);
        log.info("8888888");
        return "接收到了";
    }

    /**
     * Test 3 string.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/num/{id:\\d+}")
    public String test3(@PathVariable String id) {
        log.info(id);
        return "接收到了";
    }

    /**
     * Test 4 string.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/num/*/{id:\\d+}/get")
    public String test4(@PathVariable String id) {
        log.info(id);
        return "接收到了";
    }

    /**
     * 就版本中，/file也会默认映射为/file.*,所以例如file.pdf,file.xml也会被映射，这可能会导致黑客攻击
     * 新版本中已经取消了该功能
     *
     * @return 提示消息 string
     */
    @GetMapping("/file")
    public String test5() {
        return "接收到了";
    }
}
