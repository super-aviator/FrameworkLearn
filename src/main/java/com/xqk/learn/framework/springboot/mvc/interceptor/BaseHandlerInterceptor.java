package com.xqk.learn.framework.springboot.mvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 熊乾坤
 * @since 2019/10/7 1:00
 */
@Slf4j
@Component
public class BaseHandlerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("interceptor request ,Authorization header: {}", request.getHeader("Authorization"));
        log.info("interceptor request ,keep-alive header: {}", request.getHeader("keep-alive"));
        response.setHeader("keep-alive21", "false");
        response.setHeader("Authorization21", "999");
        return super.preHandle(request, response, handler);
    }
}
