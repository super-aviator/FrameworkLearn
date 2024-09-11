package com.xqk.lean.framework.springboot.mvc.controller;

import com.xqk.lean.framework.springboot.common.SpringBootLearnCommon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.stream.IntStream;


/**
 * 服务端向客户端发送多个对象，注意Internet Explorer并不支持这项服务器端事件推送的技术
 *
 * @author 熊乾坤
 */
@RestController
@RequestMapping("/httpStreaming")
public class HttpStreamingController {

    /**
     * 使用ResponseBodyEmitter
     *
     * @param time 重复的次数
     * @return ResponseBodyEmitter
     */
    @GetMapping("/returnMultiString")
    public ResponseBodyEmitter httpStreaming(int time) {
        ResponseBodyEmitter responseBodyEmitter = new ResponseBodyEmitter();
        SpringBootLearnCommon.CONTINER.put("responseBodyEmitter", responseBodyEmitter);
        SpringBootLearnCommon.EXECUTOR.execute(() -> {
            for (int i : IntStream.range(0, time).toArray()) {
                try {
                    Thread.sleep(500);
                    responseBodyEmitter.send(String.format("hello ResponseBodyEmitter return %d ", time));
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
            responseBodyEmitter.complete();
        });
        return responseBodyEmitter;
    }

    /**
     * Execute.
     *
     * @param time the time
     * @throws InterruptedException the interrupted exception
     * @throws IOException          the io exception
     */
    @GetMapping("/execute")
    public void execute(int time) throws InterruptedException, IOException {
        ResponseBodyEmitter responseBodyEmitter = (ResponseBodyEmitter) SpringBootLearnCommon.CONTINER.get("responseBodyEmitter");

        for (int i : IntStream.range(0, time).toArray()) {
            Thread.sleep(1000);
            responseBodyEmitter.send("hello,我返回了 " + i);
        }
        responseBodyEmitter.complete();
    }

    /**
     * 使用SseEmitter
     *
     * @param time 重复的次数
     * @return SseEmitter
     */
    @GetMapping("/sseEmitter/returnMultiString")
    public SseEmitter seeEmitter(int time) {
        SseEmitter sseEmitter = new SseEmitter();
        SpringBootLearnCommon.CONTINER.put("responseBodyEmitter", sseEmitter);
        SpringBootLearnCommon.EXECUTOR.execute(() -> {
            for (int i : IntStream.range(0, time).toArray()) {
                try {
                    Thread.sleep(500);
                    sseEmitter.send(String.format("hello SseEmitter return %d ", i));
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
            sseEmitter.complete();
        });
        return sseEmitter;
    }

    /**
     * 使用StreamingResponseBody，使用流的方式将数据传递到前端
     *
     * @return StreamingResponseBody
     */
    @GetMapping("/streamingResponseBody")
    public StreamingResponseBody download() {
        return outputStream -> outputStream.write("你好".getBytes());
    }
}
