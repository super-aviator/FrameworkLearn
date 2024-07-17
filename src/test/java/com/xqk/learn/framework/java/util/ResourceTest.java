package com.xqk.learn.framework.java.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * The type Resource test.
 */
@Slf4j
@SpringBootTest
public class ResourceTest {
    /**
     * Test.
     *
     * @throws FileNotFoundException the file not found exception
     */
    @Test
    public void test() throws FileNotFoundException {
        log.info(ResourceUtils.getURL("classpath:").toString());
        //classpath目录为target/test-classes/
    }
}
