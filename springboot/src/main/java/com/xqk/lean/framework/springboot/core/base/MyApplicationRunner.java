package com.xqk.lean.framework.springboot.core.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 熊乾坤
 * @since 2021-02-25 12:19
 */
@Slf4j
//@Component
public class MyApplicationRunner implements ApplicationRunner {
    /** 保存转换后的Map */
    private static final ConcurrentHashMap<String, String> CONVERTED_MAP = new ConcurrentHashMap<>(4);
    /** 读取配置文件中的map配置 */
    //@Value("${}")
    private Map<String, String> originalMap;

    @Override
    public void run(ApplicationArguments args) {
        originalMap.forEach((key, value) -> CONVERTED_MAP.put(DEnum.contains(key) ? DEnum.valueOf(key).name() : key, value));
    }

    private enum DEnum {
        D1, D2;

        private static boolean contains(String name) {
            return !StringUtils.isEmpty(name) && Arrays.stream(DEnum.values()).anyMatch(temp -> name.equals(temp.name()));
        }
    }
}
