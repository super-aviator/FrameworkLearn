package com.xqk.learn.framework.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.xqk.learn.framework.common.ResponseMessage;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 自定义Filter，用于验证JSON登录信息，每次请求都会匹配Filter的路径和方法，如果匹配上就会进行处理
 */
public class JSONLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(HttpMethod.POST.toString())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        try {
            JSONObject authObject = JSONObject.parseObject(request.getInputStream(), Charset.defaultCharset(), JSONObject.class);
            logger.info("JSONLoginFilter过滤器：" + authObject);
            String username = authObject.getOrDefault(this.getUsernameParameter(), "").toString();
            String password = authObject.getOrDefault(this.getPasswordParameter(), "").toString();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException(JSONObject.toJSONString(ResponseMessage.error("服务器内部错误")));
        }
    }
}
