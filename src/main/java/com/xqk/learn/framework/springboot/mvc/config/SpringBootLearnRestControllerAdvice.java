package com.xqk.learn.framework.springboot.mvc.config;

import com.xqk.learn.framework.common.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类,异常匹配是从上到下一次搜索的。
 * //注意：@RestControllerAdvice默认扫描的包路径为类所在的目录及其下面的目录
 *
 * @author Aviator
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.xqk.learn.framework.**")
public class SpringBootLearnRestControllerAdvice {

    /**
     * 处理Elasticsearch异常
     *
     * @param throwable 异常信息
     * @return ResponseMessage
     */
    @ExceptionHandler(ElasticsearchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage<String> elasticsearchExceptionHandler(Throwable throwable) {
        log.error("服务器异常", throwable);
        return ResponseMessage.error("Elasticsearch异常：" + throwable.getMessage());
    }

    /**
     * Handler bad request exception response.
     *
     * @return the response
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage<String> handlerBadRequestException(Throwable throwable) {
        log.error("服务器异常", throwable);
        return ResponseMessage.error("INTERNAL_SERVER_ERROR：" + throwable.getMessage());
    }
}