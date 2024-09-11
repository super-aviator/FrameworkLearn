package com.xqk.lean.framework.zookeeper.example.master_worker.masters;

import com.xqk.lean.framework.zookeeper.common.ZookeeperConstant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

/**
 * MasterElection
 *
 * @author xiongqiankun
 * @since 2022/9/18 16:34
 */
@Slf4j
public class ActiveMasterElection {
    private final CuratorFramework client;
    /** 是否设置了监听器 */
    private volatile boolean listener = false;

    private ActiveMasterElection(CuratorFramework client) {
        this.client = client;
        client.start();
    }

    @SneakyThrows
    public void election() {
        try {
            client.create()
                  .creatingParentsIfNeeded()
                  .withMode(CreateMode.EPHEMERAL)
                  .forPath(ZookeeperConstant.MASTER_ELECTION_PATH);
            log.info("当前节点是Active Master节点");
            //模拟心跳超时
            Thread.sleep(200000);
        } catch (Exception e) {
            log.error("", e);
            if (e instanceof KeeperException.NodeExistsException) {
                log.error("Active Master节点存活");
                if (!listener) {
                    NodeCache nodeCache = new NodeCache(client, ZookeeperConstant.MASTER_ELECTION_PATH);
                    nodeCache.getListenable()
                             .addListener(this::election);
                    nodeCache.start(true);
                    listener = true;
                }
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        ActiveMasterElection election = new ActiveMasterElection(ZookeeperConstant.CLIENT);
        election.election();
        System.in.read();
    }
}
