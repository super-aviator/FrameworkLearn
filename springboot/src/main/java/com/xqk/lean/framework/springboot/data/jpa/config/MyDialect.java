package com.xqk.lean.framework.springboot.data.jpa.config;

import org.hibernate.dialect.MySQL8Dialect;

/**
 * 配置数据库方言，目前使用的场景为新增函数，例如group_concat函数在JPA中需要使用方言去注册进去
 * 需要自定义类继承自MySQLXDialect类，X类为当前数据库使用的版本。
 * 然后在yml文件中spring.jpa.property.hibernate.dialect指定此处定义的类。
 *
 * @author 熊乾坤
 * @since 2019-11-24 16:42
 */
@SuppressWarnings("unused")
public class MyDialect extends MySQL8Dialect {
    public MyDialect() {
        super();
        // registerFunction("group_concat", new SQLFunctionTemplate(StandardBasicTypes.STRING, "group_concat(?1)"));
    }
}
