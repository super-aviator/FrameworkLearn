package com.xqk.learn.springboot.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The type Media type test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class MediaTypeTest {
    /**
     * Media.
     */
    @Test
    public void media() {
        log.info(MediaType.parseMediaTypes("application/text,text/plain").toString());
    }
}
