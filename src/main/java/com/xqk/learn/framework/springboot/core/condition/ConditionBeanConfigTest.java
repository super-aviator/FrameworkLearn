package com.xqk.learn.framework.springboot.core.condition;

import com.xqk.learn.framework.springboot.core.condition.mybean.TestBean;
import com.xqk.learn.framework.springboot.core.condition.mybean.TestBean1Impl;
import com.xqk.learn.framework.springboot.core.condition.mybean.TestBean2Impl;
import com.xqk.learn.framework.springboot.core.condition.mycondition.TestBean1Condition;
import com.xqk.learn.framework.springboot.core.condition.mycondition.TestBean2Condition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * BeanConfig
 *
 * @author xiongqiankun
 * @since 2022/9/21 15:07
 */
@Configuration
public class ConditionBeanConfigTest {
    @Bean
    @Conditional(TestBean1Condition.class)
    public TestBean bean1() {
        return new TestBean1Impl();
    }

    @Bean
    @Conditional(TestBean2Condition.class)
    public TestBean bean2() {
        return new TestBean2Impl();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment()
                          .getSystemProperties()
                          .put("condition", "bean1");
        applicationContext.register(ConditionBeanConfigTest.class);
        applicationContext.refresh();
        var bean = applicationContext.getBean(TestBean.class);
        bean.print();
    }
}
