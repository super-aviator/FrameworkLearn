package com.xqk.learn.springboot.base.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 在@Async注解中，可以指定自定义的线程池。
 *
 * @author 熊乾坤
 * @date 2020-02-21 16:39
 */

@Slf4j
@Component
public class AsyncService {

    @Async()
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
}