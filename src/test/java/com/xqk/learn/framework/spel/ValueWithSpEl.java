package com.xqk.learn.framework.spel;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ValueWithSpEl {

    @Value("#{ systemProperties['user.name'] }")
    private String location;

    @Value("#{ systemProperties['user.origin']?:'上海' }")
    private String isnull;

    @Value("#{ 1 ge 2}")
    private boolean isEqual;

    @Test
    public void test() {
        log.info(location);
        log.info(isnull);
        log.info(String.valueOf(isEqual));
    }
}
