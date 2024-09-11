package com.xqk.lean.framework.springboot.core.ioc.conversion;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * StringLocalDateConversion
 *
 * @author xiongqiankun
 * @since 2022/8/11 14:12
 */
@Component
public class StringLocalDateConverter implements Converter<String,LocalDate> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String source) {
        return LocalDate.from(DATE_TIME_FORMATTER.parse(source));
    }
}
