package com.xqk.learn.springboot.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

import static com.xqk.learn.springboot.common.SpringBootLearnCommon.CONTINER;
import static com.xqk.learn.springboot.common.SpringBootLearnCommon.EXECUTOR;

/**
 * The type Async return result controller.
 *
 * @author 熊乾坤
 */
@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncReturnResultController {

    /**
     * 返回的Callable对象会在Spring管理的线程中异步执行
     *
     * @return Callable对象 callable
     */
    @GetMapping("/callable")
    public Callable<String> asyncReturnString() {
        return () -> {
            Thread.sleep(10000);
            return "我返回了，是通过Callable异步执行的哦";
        };
    }

    /**
     * 返回的Callable对象如果想要进行超时操作，需要将其包装在WebAsyncTask中。
     *
     * @return Callable对象 web async task
     */
    @GetMapping("/callableTimeOut")
    public WebAsyncTask<String> asyncReturnStringWithTimeOut() {
        //WebAsyncTask需要手动设置超时时间，这里设置5000秒后超时
        WebAsyncTask<String> result = new WebAsyncTask<>(5000, () -> {
            Thread.sleep(10000);
            return "我返回了，是通过Callable异步执行的哦";
        });

        //必须使用这种方式（直接返回字符串吗，否则有时会打印下面的语句，有时返回404）？？？
        result.onTimeout(() -> "sorry,我超时了");

        //必须使用这种方式（直接返回字符串吗，否则有时会打印下面的语句，有时返回404）？？？
        result.onError(() -> "sorry,我出错了");

        return result;
    }

    /**
     * 返回的DeferredResult对象不在Spring管理的线程中异步执行
     * 和返回Callable的区别仅在于应用可以通过任何线程来计算返回一个结果
     *
     * @return DeferredResult deferred result
     */
    @GetMapping("/deferredResult")
    @SuppressWarnings("unchecked")
    public DeferredResult<String> asyncResultString() {
        //为DeferredResult设置500毫秒超时
        DeferredResult<String> deferredResult = new DeferredResult<>(500L);
        //将deferredResult存起来，供以后使用
        CONTINER.put("result", deferredResult);

        //超时时，可以听调用setErrorResult或者setResult重新开始处理，此时会直接返回

        deferredResult.onTimeout(() -> {
            log.info("TimeOut");
            ((DeferredResult<String>)CONTINER.get("result")).setErrorResult("sorry，我超时了");
        });

        return deferredResult;
    }

    /**
     * 使用非Spring管理的线程去调用DeferredResult产生结果
     */
    @GetMapping("/operate")
    @SuppressWarnings("unchecked")
    public void operateDeferredResult() {
        EXECUTOR.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((DeferredResult<String>)CONTINER.get("result")).setResult("我返回了，是通过DeferredResult异步执行的哦");
        });
    }
}
