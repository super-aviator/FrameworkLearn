package com.xqk.learn.framework.mvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

/**
 * The type Media type test.
 */
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
