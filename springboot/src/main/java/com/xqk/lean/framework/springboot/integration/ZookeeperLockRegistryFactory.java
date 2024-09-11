package com.xqk.lean.framework.springboot.integration;

import lombok.RequiredArgsConstructor;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.integration.support.locks.ExpirableLockRegistry;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;
import org.springframework.stereotype.Component;

/**
 * ZookeeperLockRegistryFactory
 *
 * @author xiongqiankun
 * @since 2022/10/26 20:48
 */
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "lock", value = "mode", havingValue = "zookeeper")
public class ZookeeperLockRegistryFactory implements LockRegistryFactory {
    private final CuratorFramework curatorFramework;

    @Override
    public ExpirableLockRegistry createLockRegistry(String prefix, long expireAfter) {
        return new ZookeeperLockRegistry(curatorFramework, prefix);
    }
}
