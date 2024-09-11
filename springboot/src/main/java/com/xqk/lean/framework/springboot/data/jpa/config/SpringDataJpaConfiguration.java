package com.xqk.lean.framework.springboot.data.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * SpringDataJpaConfig
 *
 * @author xiongqiankun
 * @since 2022/1/26 10:30
 */
@EntityScan("com.xqk.lean.framework.springboot.data.jpa.entity")
@EnableJpaRepositories("com.xqk.lean.framework.springboot.data.jpa.repository")
@Configuration
public class SpringDataJpaConfiguration {
}
