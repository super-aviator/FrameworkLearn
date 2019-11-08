package com.xqk.learn.springboot.common;

import lombok.Data;

/**
 * 封装的消息返回体
 *
 * @author 熊乾坤
 * @since 2019-11-02 15:27
 */
@Data
public class ResponseMessage<T> {

    private int status;

    private T data;

    private int code;

    private ResponseMessage() {
    }

    public static <T> ResponseMessage<T> ok(T data) {
        ResponseMessage<T> responseMessage = new ResponseMessage<>();
        responseMessage.setData(data);
        responseMessage.setStatus(200);
        return responseMessage;
    }

    public static <T> ResponseMessage<T> error(T data) {
        ResponseMessage<T> responseMessage = new ResponseMessage<>();
        responseMessage.setData(data);
        responseMessage.setStatus(500);
        return responseMessage;
    }

    public static <T> ResponseMessage<T> generic(int status, T data) {
        ResponseMessage<T> responseMessage = new ResponseMessage<>();
        responseMessage.setData(data);
        responseMessage.setStatus(status);
        return responseMessage;
    }

    public static <T> ResponseMessage<T> generic(int status, int code, T data) {
        ResponseMessage<T> responseMessage = new ResponseMessage<>();
        responseMessage.setData(data);
        responseMessage.setStatus(status);
        responseMessage.setCode(code);
        return responseMessage;
    }
}
