package com.xqk.lean.framework.zookeeper.curator.tools;

import com.netflix.curator.framework.recipes.locks.InterProcessMutex;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import static com.xqk.learn.framework.zookeeper.common.ZookeeperConstant.CLIENT;
import static com.xqk.learn.framework.zookeeper.common.ZookeeperConstant.DISTRIBUTED_LOCK_PATH;

/**
 * DistributedLock
 * 使用Curator实现分布式锁
 *
 * @author xiongqiankun
 * @since 2022/3/10 19:26
 */
@Slf4j
public class DistributedLock {

    public static void main(String[] args) throws Exception {
        var cdl = new CountDownLatch(1);
        int counter = 0;
        CLIENT.start();
        var lock = new InterProcessMutex(CLIENT, DISTRIBUTED_LOCK_PATH);
        var executors = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executors.submit(() -> {
                try {
                    cdl.await();
                    lock.acquire();
                    //临界区
                    log.info("{} counter：{}", Thread.currentThread().getName(), System.currentTimeMillis());
                } catch (Exception e) {
                    log.error("", e);
                } finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        log.error("", e);
                    }
                }
            });
            cdl.countDown();
        }
    }
}
