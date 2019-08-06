package com.springboot.learn.mvc.controller;

import com.springboot.learn.common.SpringBootLearnCommon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.stream.IntStream;


/**
 * The type Http streaming controller.
 *
 * @author 熊乾坤
 */
@RestController
@RequestMapping("/httpStreaming")
public class HttpStreamingController {

    /**
     * Http streaming response body emitter.
     *
     * @return the response body emitter
     */
    @GetMapping("/returnMultiString")
    public ResponseBodyEmitter httpStreaming(){
        ResponseBodyEmitter responseBodyEmitter=new ResponseBodyEmitter();
        SpringBootLearnCommon.CONTINER.put("responseBodyEmitter",responseBodyEmitter);
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
        ResponseBodyEmitter responseBodyEmitter= (ResponseBodyEmitter) SpringBootLearnCommon.CONTINER.get("responseBodyEmitter");

        for(int i: IntStream.range(0,time).toArray()){
            Thread.sleep(1000);
            responseBodyEmitter.send("hello,我返回了 " + i);
        }
        responseBodyEmitter.complete();
    }
}
