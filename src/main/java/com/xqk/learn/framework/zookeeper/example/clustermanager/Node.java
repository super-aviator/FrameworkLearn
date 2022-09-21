package com.xqk.learn.framework.zookeeper.example.clustermanager;

import org.apache.zookeeper.CreateMode;

import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.framework.recipes.cache.PathChildrenCache;
import com.netflix.curator.framework.recipes.cache.PathChildrenCacheEvent;
import com.netflix.curator.retry.ExponentialBackoffRetry;
import com.xqk.learn.framework.zookeeper.common.ZookeeperConstant;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Node
 *
 * @author xiongqiankun
 * @since 2022/3/11 14:16
 */
@Slf4j
public class Node extends AbstractClusterMachine implements Runnable {
    private boolean isMaster;

    public Node(String name) {
        super(CuratorFrameworkFactory.builder()
                                     .connectString(ZookeeperConstant.CONNECT_STRING)
                                     .sessionTimeoutMs(ZookeeperConstant.SESSION_TIMEOUT)
                                     .connectionTimeoutMs(ZookeeperConstant.CONNECTION_TIMEOUT)
                                     //chroot命名空间隔离
                                     .namespace(ZookeeperConstant.CHROOT)
                                     .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                                     .build(), name);
    }

    @Override
    public void run() {
        start();
        while (true) {
            online();
        }
    }

    @Override
    public void stop() {
        super.stop();
    }

    @SneakyThrows
    public void online() {
        tryToBeMaster();
        if (!isMaster) {
            var watch = new PathChildrenCache(client, ZookeeperConstant.CLUSTER_PATH, true);
            watch.start();
            watch.getListenable().addListener((client, event) -> {
                if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                    //尝试将自己选举为master
                    tryToBeMaster();
                    //业务逻辑
                    business();
                }
            });
            watch.wait();
        }
        //业务逻辑
        business();
    }

    public void tryToBeMaster() {
        //尝试Master选举
        try {
            client.create()
                  .creatingParentsIfNeeded()
                  .withMode(CreateMode.EPHEMERAL)
                  .forPath(ZookeeperConstant.CLUSTER_MASTER_PATH);
            isMaster = true;
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
        if (isMaster) {
            log.info("当前节点{}成为Master节点", name);
        } else {
            log.info("当前节点{}未成为Master节点", name);
        }
    }

    public void abandonMaster() {
        try {
            client.delete()
                  .forPath(ZookeeperConstant.CLUSTER_MASTER_PATH);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
        isMaster = false;
    }

    public void business() {
        if (isMaster) {
            //如果是Master节点，轮询子节点，然后退出
            int i = 20;
            while (i-- > 0) {
                try {
                    var childrenSet = client.getChildren()
                                            .forPath(ZookeeperConstant.CLUSTER_INSTANCE_PATH);
                    log.info("Master：{} 当前节点列表：{}", name, childrenSet);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    log.error("", e);
                }
            }
            log.info("Master：{} 当前节点放弃Master权限", name);
            abandonMaster();
        } else {
            //如果是子节点，啥也不干，等待master节点退出
        }
    }
}
