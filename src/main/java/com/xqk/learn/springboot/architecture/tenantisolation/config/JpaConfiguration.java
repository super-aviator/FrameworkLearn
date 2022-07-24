package com.xqk.learn.springboot.architecture.tenantisolation.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JpaConfiguration
 *
 * @author xiongqiankun
 * @since 2022/5/15 14:29
 */
@EntityScan("com.xqk.learn.springboot.architecture.tenantisolation.entity")
@EnableJpaRepositories("com.xqk.learn.springboot.architecture.tenantisolation.repository")
@Configuration
public class JpaConfiguration {
}
