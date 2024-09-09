package com.xqk.learn.framework.springboot.core.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 在@Async注解中，可以指定自定义的线程池。
 *
 * @author 熊乾坤
 * @since 2020-02-21 16:39
 */

@Slf4j
@Component
@Profile("schedule")
public class AsyncService {

    public static void main(String[] args) {
        Map<String, Double> maxSpeedMap = new HashMap<>(16);
        maxSpeedMap.put("123", null);
        System.out.println(maxSpeedMap.containsKey("123"));
    }

    @Async
    public CompletableFuture<String> getHtmlText() throws IOException {
        log.info("async task start");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.zhihu.com");
        try (CloseableHttpResponse response = client.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
            log.info("async task end");
            return CompletableFuture.completedFuture(br.lines().collect(Collectors.joining()));
        }
    }

    @Async
    public void simpleAsyncMethod() {
        log.info("simple async method executing!");
    }

    @Async
    public void asyncMethod() {
        log.info("async method executing ,will invoke simple async method!");
        //同一个类中调用@Async方法，不是异步执行的
        simpleAsyncMethod();
    }

    @Async
    public void throwExceptionAsyncMethod() {
        throw new RuntimeException();
    }
}