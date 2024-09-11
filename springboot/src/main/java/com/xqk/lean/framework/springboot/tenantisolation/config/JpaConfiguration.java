package com.xqk.lean.framework.springboot.tenantisolation.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JpaConfiguration
 *
 * @author xiongqiankun
 * @since 2022/5/15 14:29
 */
@Configuration
@EntityScan("com.xqk.lean.framework.springboot.tenantisolation.entity")
@EnableJpaRepositories("com.xqk.lean.framework.springboot.tenantisolation.repository")
public class JpaConfiguration {
}
