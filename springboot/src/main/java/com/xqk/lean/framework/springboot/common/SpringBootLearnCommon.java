package com.xqk.lean.framework.springboot.common;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Spring boot learn common.
 */
public class SpringBootLearnCommon {
    /**
     * 自己的线程池
     */
    public static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    /**
     * The constant CONTINER.
     */
    public static final Map<String,Object> CONTINER=new HashMap<>(16);
}
