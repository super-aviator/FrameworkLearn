package com.xqk.learn.framework.springboot.core.spel.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 熊乾坤
 * @date 2020-05-30 22:37
 */
@Data
@Component
@NoArgsConstructor
public class SPELProperties {
    private String property1;

    private String property2;

    private String property3;

    @Autowired
    public SPELProperties(@Value(value = "Hello") String property1, @Value(value = "World") String property2, @Value(value = "!") String property3) {
        this.property1 = property1;
        this.property2 = property2;
        this.property3 = property3;
    }
}
