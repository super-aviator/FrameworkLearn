package com.xqk.learn.springboot.data.jpa.datasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 配置两个数据源，一个连接Oracle，一个连接Mysql，MySQL数据源使用JPA自动配置的数据源
 * 即使是使用MySQL的默认的配置，依旧需要在此处申明，否则无法启动
 *
 * @author 熊乾坤
 * @since 2020-01-06 9:57
 */
@Configuration
@Profile("datasource")
class TwoDatasourceConfiguration {

    @Bean("oracle")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource getOracleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("mysql")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getMysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getOracleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(getOracleDataSource())
                .packages("com.xqk.learn.springboot.data.jpa.datasource.entity")
                .persistenceUnit("oracle")
                .build();

    }

    @Primary
    @Bean("mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getMysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(getMysqlDataSource())
                .packages("com.xqk.learn.springboot.data.jpa.entity")
                .persistenceUnit("mysql")
                .build();

    }

    @Bean("oracleTransactionManager")
    public PlatformTransactionManager getOraclePlatformTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(getOracleEntityManagerFactory(builder).getObject());
    }

    @Primary
    @Bean("mysqlTransactionManager")
    public PlatformTransactionManager getMysqlPlatformTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(getMysqlEntityManagerFactory(builder).getObject());
    }
}
