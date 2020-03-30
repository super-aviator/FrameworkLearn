package com.xqk.learn.springboot.mvc.exception;

import com.xqk.learn.springboot.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类,异常匹配是从上到下一次搜索的。
 *
 * @author Aviator
 */
@RestControllerAdvice
public class SpringBootLearnExceptionHandler {

    /**
     * Handler bad request exception response.
     *
     * @return the response
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage handlerBadRequestException() {
        return ResponseMessage.error("INTERNAL_SERVER_ERROR");
    }
}