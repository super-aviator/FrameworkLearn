package com.xqk.lean.framework.springboot.tenantisolation.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 多租户AOP类，用于拦截所有调用数据库的方法，将租户的Tenant注入到Filter中。
 *
 * @author 熊乾坤
 * @since 2020-03-30 10:00
 */
@Aspect
@Component
public class TenantIsolationAspect {

}
