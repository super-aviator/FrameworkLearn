package com.xqk.lean.framework.springboot.transaction.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * TransactionConfig
 *
 * @author xiongqiankun
 * @since 2022/5/15 16:26
 */
@EntityScan("com.xqk.lean.framework.springboot.transaction.entity")
@EnableJpaRepositories("com.xqk.lean.framework.springboot.transaction.dao")
@Configuration
public class TransactionConfig {
}
