package com.xqk.learn.framework.springboot.core.environment;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 熊乾坤
 * @since 2021-03-27 10:18
 */
@RestController
@RequestMapping(value = "/environment")
public class EnvironmentController {

    private final Environment environment;

    public EnvironmentController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping
    public String getProperty(@RequestParam String key) {
        return environment.getProperty(key);
    }
}
