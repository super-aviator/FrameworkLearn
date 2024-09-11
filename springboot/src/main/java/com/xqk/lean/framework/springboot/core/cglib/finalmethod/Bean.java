package com.xqk.lean.framework.springboot.core.cglib.finalmethod;

import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@Component
public class Bean {
    private final String str;

    public Bean() {
        str = "熊乾坤";
    }
}