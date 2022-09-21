package com.xqk.learn.framework.springboot.core.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.ImmutableBean;

/**
 * 利用ImmutableBean工具类，可以创建出一个不能修改的Bean
 */
@Slf4j
public class ImmutableBeanTest {
    private static class Bean{
        private String name;

        public Bean() {
        }

        public Bean(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Bean bean= (Bean) ImmutableBean.create(new Bean("name"));
        log.info(bean.getName());
        bean.setName("熊乾坤");
    }
}
