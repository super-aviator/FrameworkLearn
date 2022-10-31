package com.xqk.learn.framework.springboot.integration;

import org.springframework.integration.support.locks.ExpirableLockRegistry;
import org.springframework.integration.support.locks.LockRegistry;

/**
 * LockRegistryFactory
 *
 * @author xiongqiankun
 * @since 2022/10/26 20:37
 */
public interface LockRegistryFactory {
    /**
     * 根据配置获取LockRegistry
     * <p>
     *
     * @param prefix      prefix
     * @param expireAfter 超时时间
     * @return LockRegistry
     */
    ExpirableLockRegistry createLockRegistry(String prefix, long expireAfter);
}
