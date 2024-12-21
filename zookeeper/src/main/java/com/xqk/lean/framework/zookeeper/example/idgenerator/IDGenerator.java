package com.xqk.lean.framework.zookeeper.example.idgenerator;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.Executors;

import static com.xqk.lean.framework.zookeeper.common.ZookeeperConstant.CLIENT;


/**
 * IDGenerator
 * 使用Zookeeper实现分布式ID生成器
 *
 * @author xiongqiankun
 * @since 2022/3/11 10:52
 */
@Slf4j
public class IDGenerator {
    @SneakyThrows
    public static void main(String[] args) {
        CLIENT.start();
        var executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                String id = null;
                try {
                    id = CLIENT.create()
                               .creatingParentsIfNeeded()
                               .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                               .forPath("/id-generator/business1/pro1-");
                } catch (Exception e) {
                    log.error("", e);
                }
                log.info("{} generated id is:{}", Thread.currentThread().getName(), id);
            });
            executor.shutdown();
        }
    }
}
