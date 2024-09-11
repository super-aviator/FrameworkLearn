package com.xqk.lean.framework.springboot.data.jpa.datasource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 熊乾坤
 * @since 2020-01-06 13:41
 */
@Configuration
@Profile("datasource")
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.xqk.lean.framework.springboot.data.jpa.repository"},
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager"
)
public class MysqlJpaRepositoryConfiguration {
}
