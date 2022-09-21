package com.xqk.learn.framework.zookeeper.curator.sync;

import static com.xqk.learn.framework.zookeeper.common.ZookeeperConstant.CLIENT;

import java.nio.charset.StandardCharsets;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import lombok.extern.slf4j.Slf4j;

/**
 * CuratorLearn
 * Curator同步API学习
 *
 * @author xiongqiankun
 * @since 2022/3/10 14:07
 */
@Slf4j
public class SyncCuratorLearn {
    public static void main(String[] args) throws Exception {
        CLIENT.start();
        CLIENT.create()
              .creatingParentsIfNeeded()
              .withMode(CreateMode.EPHEMERAL)
              .forPath("/zookeeper/abc");
        log.info("children-------/zookeeper-------{}", CLIENT.getChildren().forPath("/zookeeper"));
        var stat = new Stat();
        CLIENT.getData().storingStatIn(stat).forPath("/zookeeper/abc");
        CLIENT.setData().forPath("/zookeeper/abc", "learn".getBytes(StandardCharsets.UTF_8)).setVersion(stat.getVersion());
        try {
            CLIENT.setData().withVersion(stat.getVersion()).forPath("/zookeeper/abc", "learn".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("", e);
        }
        log.info("data-------/zookeeper/abc-------{}", new String(CLIENT.getData().forPath("/zookeeper/abc")));
    }
}
