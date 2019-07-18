package com.springboot.learn.common;

/**
 * The type Response.
 *
 * @param <T> the type parameter
 * @author Aviator
 */
public class Response<T> {

    private int code;

    private String msg;

    private T data;

    /**
     * Instantiates a new Response.
     *
     * @param code the code
     */
    public Response(int code) {
        this.code = code;
    }

    /**
     * Instantiates a new Response.
     *
     * @param code the code
     * @param msg  the msg
     * @param data the data
     */
    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * Instantiates a new Response.
     *
     * @param code the code
     * @param msg  the msg
     */
    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Instantiates a new Response.
     */
    public Response() {
        this(404);
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseMessageDTO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
