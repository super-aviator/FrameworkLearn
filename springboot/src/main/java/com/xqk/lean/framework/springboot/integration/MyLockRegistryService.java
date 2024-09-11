package com.xqk.lean.framework.springboot.integration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.locks.ExpirableLockRegistry;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ConcurrentHashMap;

/**
 * MyLockRegisFactory
 *
 * @author xiongqiankun
 * @since 2022/10/26 20:29
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class MyLockRegistryService {
    private final ConcurrentHashMap<String,ExpirableLockRegistry> registries = new ConcurrentHashMap<>();
    private final LockRegistryFactory lockRegistryFactory;

    @Value("${base.lock.lockExpireAge:60000}")
    private long lockExpireAge = 60000L;

    public ExpirableLockRegistry createLockRegistry(String prefix, long expireAfter) {
        return registries.computeIfAbsent(prefix, (key)->lockRegistryFactory.createLockRegistry(prefix, expireAfter));
    }

    /**
     * 每分钟执行一次锁回收任务, 避免内存泄露
     */
    @Scheduled(fixedDelayString = "${base.lock.expireUnusedDelay:60000000}")
    public void cleanLocks() {
        log.info("expiring unused redis locks");
        registries.values()
                  .forEach(registry->registry.expireUnusedOlderThan(lockExpireAge));
        log.info("unused locks expired");
    }
}
