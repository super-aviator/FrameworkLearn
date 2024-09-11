package com.xqk.lean.framework.zookeeper.example.clustermanager;

import com.xqk.lean.framework.zookeeper.common.ZookeeperConstant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

/**
 * AbstractClusterMachine
 *
 * @author xiongqiankun
 * @since 2022/3/11 14:17
 */
@Slf4j
public abstract class AbstractClusterMachine implements ClusterMachine {
    protected final CuratorFramework client;
    protected final String name;
    protected String path;

    public AbstractClusterMachine(CuratorFramework client, String name) {
        this.client = client;
        this.name = name;
    }

    @SneakyThrows
    @Override
    public void start() {
        client.start();
        client.getZookeeperClient().blockUntilConnectedOrTimedOut();
        //向zookeeper注册
        try {
            path = client.create()
                         .creatingParentsIfNeeded()
                         .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                         .forPath(ZookeeperConstant.CLUSTER_INSTANCE_PATH + "/" + name + "-");
        } catch (Exception e) {
            log.error("", e);
        }
        log.info("节点上线成功：{}", path);
    }

    @Override
    public void stop() {
        client.close();
        log.info("节点下线：{}", path);
    }
}
