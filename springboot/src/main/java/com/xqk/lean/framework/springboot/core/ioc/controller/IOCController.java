package com.xqk.lean.framework.springboot.core.ioc.controller;

import com.xqk.lean.framework.springboot.common.ResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * IOCController
 *
 * @author xiongqiankun
 * @since 2022/8/11 14:17
 */
@RestController
@RequestMapping("/ioc")
public class IOCController {
    @GetMapping("/conversion")
    public ResponseMessage<String> conversion(@RequestParam("date") LocalDate localDate) {
        return ResponseMessage.ok(localDate.toString());
    }
}
