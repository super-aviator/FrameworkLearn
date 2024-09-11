package com.xqk.lean.framework.springboot.junit.framework.config;

import com.xqk.learn.framework.common.Foo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * 注解@TestConfigurationTest与注解@Configuration相同，不过是测试专用的Configuration，有以下特性：
 * 1. @TestConfigurationTest创建的Bean能够覆盖@Configuration注解的类生成的bean
 * 2. 需要在@SpringBootApplication注解中使用TypeExcludeFilter进行排除
 * <p>
 * 推荐在测试类的内部类中使用@TestConfigurationTest，这样非测试时不会被扫描到
 *
 * @author xiongqiankun
 * @since 2022/1/25 19:37
 */
@TestConfiguration
public class MyTestConfiguration {
    @Bean
    public Foo foo1() {
        return new Foo("foo1");
    }

    @Bean("mysql")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getMysqlDataSource() {
        return DataSourceBuilder.create()
                                .build();
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getMysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(getMysqlDataSource())
                      .packages("com.xqk.learn.framework.data.jpa.entity")
                      .persistenceUnit("mysql")
                      .build();

    }
}
