package com.xqk.learn.framework.zookeeper.curator.tools;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.framework.recipes.leader.LeaderSelector;
import com.netflix.curator.framework.recipes.leader.LeaderSelectorListener;
import com.netflix.curator.framework.state.ConnectionState;
import com.netflix.curator.retry.ExponentialBackoffRetry;
import com.xqk.learn.framework.zookeeper.common.ZookeeperConstant;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

import static com.xqk.learn.framework.zookeeper.common.ZookeeperConstant.MASTER_ELECTION_PATH;

/**
 * LeaderElection
 * 使用Curator实现Leader选举
 * takeLeadership执行完毕就会放弃Leader权限，在未获取到Leader权限时，线程会阻塞，直到获取到Leader权限
 *
 * @author xiongqiankun
 * @since 2022/3/10 19:04
 */
@Slf4j
public class LeaderElection extends Thread {
    private volatile boolean isActive;
    private final CuratorFramework client;
    private final LeaderSelector leaderSelector;

    public LeaderElection() {
        this.client = CuratorFrameworkFactory.builder()
                                             .authorization("digest", ZookeeperConstant.AUTH.getBytes(StandardCharsets.UTF_8))
                                             .connectString(ZookeeperConstant.CONNECT_STRING)
                                             .sessionTimeoutMs(ZookeeperConstant.SESSION_TIMEOUT)
                                             .connectionTimeoutMs(ZookeeperConstant.CONNECTION_TIMEOUT)
                                             //chroot命名空间隔离
                                             .namespace(ZookeeperConstant.CHROOT)
                                             .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                                             .build();

        this.leaderSelector = new LeaderSelector(client, MASTER_ELECTION_PATH, leaderSelectorListener);
        leaderSelector.setId(this.getName());
    }

    @Override
    public void run() {
        client.start();
        leaderSelector.autoRequeue();
        leaderSelector.start();
    }

    /**
     * 选举监听器，用于监听选举结果
     */
    public LeaderSelectorListener leaderSelectorListener = new LeaderSelectorListener() {
        @Override
        public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
            if (connectionState == ConnectionState.SUSPENDED || connectionState == ConnectionState.LOST) {
                throw new RuntimeException();
            }
        }

        @Override
        public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
            try {
                log.info("[{}]成为Active Master  Participant:[{}]", leaderSelector.getId(), leaderSelector.getParticipants());
                isActive = true;
                //do something
                Thread.sleep(10000);
            } finally {
                log.info("[{}]成为Backup Worker", leaderSelector.getId());
                isActive = false;
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new LeaderElection();
            threads[i].start();
        }
        Thread.sleep(Integer.MAX_VALUE);
    }
}
