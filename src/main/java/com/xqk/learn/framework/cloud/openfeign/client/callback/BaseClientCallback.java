package com.xqk.learn.framework.cloud.openfeign.client.callback;

import com.xqk.learn.framework.cloud.openfeign.client.BaseClient;
import org.springframework.stereotype.Component;

/**
 * @author 熊乾坤
 * @since 2020-12-28 19:00
 */
@Component
public class BaseClientCallback implements BaseClient {
    @Override
    public String getMessage() {
        return "发生了熔断";
    }

    @Override
    public String getMessageAfterSleepMilliSeconds(Long milliSeconds) {
        return "发生了熔断";
    }
}
