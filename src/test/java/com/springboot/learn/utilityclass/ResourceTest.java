package com.springboot.learn.utilityclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ResourceTest {
    @Test
    public void test() throws FileNotFoundException {
        log.info(ResourceUtils.getURL("classpath:").toString());
        //classpath目录为target/test-classes/
    }
}
