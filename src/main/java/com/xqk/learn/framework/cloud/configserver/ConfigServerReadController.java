package com.xqk.learn.framework.cloud.configserver;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 熊乾坤
 * @since 2021-03-27 0:59
 */
@RestController
@Profile("microservices")
@RequestMapping("/config-server")
public class ConfigServerReadController {
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @GetMapping
    public JSONObject getAttribute() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        return jsonObject;
    }
}
