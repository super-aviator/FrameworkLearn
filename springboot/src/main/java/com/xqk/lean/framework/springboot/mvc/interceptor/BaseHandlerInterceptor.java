package com.xqk.lean.framework.springboot.mvc.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

/**
 * @author 熊乾坤
 * @since 2019/10/7 1:00
 */
@Slf4j
@Component
public class BaseHandlerInterceptor extends WebRequestHandlerInterceptorAdapter {

    public BaseHandlerInterceptor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("interceptor request ,Authorization header: {}", request.getHeader("Authorization"));
        log.info("interceptor request ,keep-alive header: {}", request.getHeader("keep-alive"));
        response.setHeader("keep-alive21", "false");
        response.setHeader("Authorization21", "999");
        return super.preHandle(request, response, handler);
    }

}
