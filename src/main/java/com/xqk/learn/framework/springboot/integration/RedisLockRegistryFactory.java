package com.xqk.learn.framework.springboot.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.support.locks.ExpirableLockRegistry;

/**
 * RedisLockRegistryFactory
 *
 * @author xiongqiankun
 * @since 2022/10/26 20:48
 */

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "lock", value = "mode", havingValue = "redis", matchIfMissing = true)
public class RedisLockRegistryFactory implements LockRegistryFactory {
    private final RedisConnectionFactory redisConnectionFactory;

    @Override
    public ExpirableLockRegistry createLockRegistry(String prefix, long expireAfter) {
        return new RedisLockRegistry(redisConnectionFactory, prefix, expireAfter);
    }
}
