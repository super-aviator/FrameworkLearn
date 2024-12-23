package com.xqk.lean.framework.springboot.core.condition.mycondition;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Bean1Condition
 *
 * @author xiongqiankun
 * @since 2022/9/21 15:06
 */
public class TestBean2Condition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var map=metadata.getAllAnnotationAttributes(Test.class.getName());
        return "bean2".equals(context.getEnvironment()
                                     .getProperty("condition"));
    }
}
