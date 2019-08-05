package com.springboot.learn.common;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpringBootLearnCommon {
    /**
     * 自己的线程池
     */
    public static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    public static final Map<String,Object> CONTINER=new HashMap<>(16);
}
