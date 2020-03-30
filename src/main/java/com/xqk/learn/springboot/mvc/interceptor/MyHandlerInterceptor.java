package com.xqk.learn.springboot.mvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * 自定义的拦截器，需要在HandlerInterceptorRegister中去注册
 *
 * @author 熊乾坤
 */
@Component
@Slf4j
public class MyHandlerInterceptor extends HandlerInterceptorAdapter {
    private int beginHourOfDay = 8;
    private int endHourOfDay = 9;

    public MyHandlerInterceptor() {
    }

    public MyHandlerInterceptor(int startTime, int endTime) {
        beginHourOfDay = startTime;
        endHourOfDay = endTime;
    }

    public void setBeginHourOfDay(int beginHourOfDay) {
        this.beginHourOfDay = beginHourOfDay;
    }

    public void setEndHourOfDay(int endHourOfDay) {
        this.endHourOfDay = endHourOfDay;
    }

    /**
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  handler
     * @return 是否过滤该请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info("interceptor request ,url: {}", request.getRequestURI());
        log.info("interceptor request ,headers: {}", request.getHeaderNames().toString());
        response.setCharacterEncoding("utf-8");
        Calendar calendar = Calendar.getInstance();
        int currentTime = calendar.get(Calendar.HOUR_OF_DAY);
        if (currentTime >= beginHourOfDay && currentTime <= endHourOfDay) {
            return true;
        }
        response.getWriter().write(String.format("sorry , Please %d-%d time access", beginHourOfDay, endHourOfDay));
        return false;
    }

    /**
     * HandlerInterceptor的后拦截postHandle方法不一定总是适用于注解了@ResponseBody或ResponseEntity的方法。
     * 这些场景中，HttpMessageConverter会在拦截器的postHandle方法被调之前就把信息写回响应中。
     * 这样拦截器就无法再改变响应了，比如要增加一个响应头之类的。
     * 如果有这种需求，请让你的应用实现ResponseBodyAdvice接口，并将其定义为一个@ControllerAdvicebean或直接在RequestMappingHandlerMapping中配置。
     *
     * @param request      request
     * @param response     response
     * @param handler      handler
     * @param modelAndView modelAndView
     * @throws Exception Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
