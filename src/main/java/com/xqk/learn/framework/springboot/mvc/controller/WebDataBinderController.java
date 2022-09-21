package com.xqk.learn.framework.springboot.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 设置自定义的数据绑定
 * <p>
 * Spring内置支持将一个逗号分隔的字符串（或其他类型转换系统所能识别的类型）转换成一个String类型的列表/集合。
 * 举个例子，一个注解了@RequestHeader("Accept")的方法参数可以是一个String类型，但也可以是String[]或List<String>类型的。
 *
 * @author 熊乾坤
 */
@RestController
@Slf4j
@RequestMapping("/webDataBinder")
public class WebDataBinderController {
    /**
     * Test string.
     *
     * @param date       the date
     * @param name       the name
     * @param stringList the string list
     * @return the string
     */
    @GetMapping("/split")
    public String test(Date date, String name, List<String> stringList) {
        return Arrays.asList(date, stringList, name).toString();
    }

    /**
     * 注意，@InitBinder方法也可以定义在@ControllerAdvice注解的类上，这样配置可以为许多控制器所共享
     *
     * @param binder WebDataBinder
     */
    @InitBinder
    public void bindData(WebDataBinder binder) {
        //取消name字段的绑定(貌似未生效。。)
        binder.setDisallowedFields("name");
        //设置前端传过来的时间的格式，和@DateTimeFormat功能类似
        binder.addCustomFormatter(new DateFormatter("yyyy*mm*dd"));
    }
}