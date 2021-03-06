package com.xqk.learn.springboot.cloud.openfeign.client;

import com.xqk.learn.springboot.cloud.openfeign.config.BaseClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 熊乾坤
 * @since 2020-12-27 17:02
 */
@FeignClient(name = "baseClient", url = "http://localhost:8000", configuration = BaseClientConfiguration.class)
public interface BaseClient {
    @GetMapping("/base/get")
    String getMessage();

    @GetMapping("/base/get/sleep/{milliSeconds}")
    String getMessageAfterSleepMilliSeconds(@PathVariable Long milliSeconds);
}