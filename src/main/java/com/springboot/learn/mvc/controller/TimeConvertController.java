package com.springboot.learn.mvc.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * The type Time convert controller.
 *
 * @author 熊乾坤
 */
@RestController
@RequestMapping("/time")
@Slf4j
public class TimeConvertController {
    /**
     * StringMVC中默认支持的时间格式为yyyy/MM/dd hh:mm:ss和yyyy/M/d h:m:s
     *
     * @param date 前端传过来的date字符串
     */
    @GetMapping("/receive-string")
    public void timeWithConvertString(Date date) {
        log.info(date.toString());
    }

    /**
     * 使用@DateTimeFormat注解指定前端的时间格式为yyyy@MM@dd
     *
     * @param date 前端传过来的date字符串
     */
    @GetMapping(value = "/format-time")
    public void formatTime(@DateTimeFormat(pattern = "yyyy@MM@dd") Date date) {
        log.info(date.toString());
    }

    /**
     * 在实体类上使用@JsonFormat(pattern = "yyyy@MM@dd")定义返回的JSON数据的格式
     *
     * @return 返回的json对象 return time dto
     */
    @GetMapping(value = "/return-date")
    public ReturnTimeDTO returnDate() {
        return new ReturnTimeDTO(new Date());
    }

    @Getter
    @ToString
    @AllArgsConstructor
    private static class ReturnTimeDTO {
        /**
         * 定义返回的时间格式以及时区，市区必须要指定。
         * 因为，jackson在序列化时间时是按照国际标准时间GMT进行格式化的，而在国内默认时区使用的是CST时区，两者相差8小时。
         */
        @JsonFormat(pattern = "yyyy@MM@dd HH%mm%dd", timezone = "GMT+8")
        private Date date;
    }
}
