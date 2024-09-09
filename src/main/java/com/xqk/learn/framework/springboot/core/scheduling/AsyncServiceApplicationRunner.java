package com.xqk.learn.framework.springboot.core.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.CompletableFuture;

/**
 * @author 熊乾坤
 * @since 2020-02-21 16:59
 */
@Slf4j
//@Component
@Profile("schedule")
public class AsyncServiceApplicationRunner implements ApplicationRunner {
    private final AsyncService asyncService;

    public AsyncServiceApplicationRunner(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CompletableFuture<String> future1 = asyncService.getHtmlText();
        CompletableFuture<String> future2 = asyncService.getHtmlText();
        CompletableFuture<String> future3 = asyncService.getHtmlText();
        CompletableFuture<String> future4 = asyncService.getHtmlText();
        CompletableFuture<String> future5 = asyncService.getHtmlText();

        CompletableFuture.allOf(future1, future2, future3, future4, future5).join();

        log.info("async1 运行结果：{}", future1.get());
        log.info("async2 运行结果：{}", future2.get());
        log.info("async3 运行结果：{}", future3.get());
        log.info("async4 运行结果：{}", future4.get());
        log.info("async5 运行结果：{}", future5.get());

        asyncService.asyncMethod();
        //测试抛出异常
        asyncService.throwExceptionAsyncMethod();
    }
}
