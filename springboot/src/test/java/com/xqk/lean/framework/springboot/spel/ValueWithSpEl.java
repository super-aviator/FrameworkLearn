package com.xqk.lean.framework.springboot.spel;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
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
