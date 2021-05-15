package com.xqk.learn.springboot.core.ioc.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactory implements FactoryBean<MyBean> {
    private static long id=0;

    @Override
    public MyBean getObject() {
        return new MyBean(id++);
    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
