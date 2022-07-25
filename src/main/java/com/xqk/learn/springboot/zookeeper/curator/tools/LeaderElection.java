package com.xqk.learn.springboot.zookeeper.curator.tools;

import static com.xqk.learn.springboot.zookeeper.common.ZookeeperConstant.CLIENT;
import static com.xqk.learn.springboot.zookeeper.common.ZookeeperConstant.LEADER_ELECTION_PATH;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.recipes.leader.LeaderSelector;
import com.netflix.curator.framework.recipes.leader.LeaderSelectorListener;
import com.netflix.curator.framework.state.ConnectionState;

import lombok.extern.slf4j.Slf4j;

/**
 * LeaderElection
 * 使用Curator实现Leader选举
 * takeLeadership执行完毕就会放弃Leader权限，在未获取到Leader权限时，线程会阻塞，直到获取到Leader权限
 *
 * @author xiongqiankun
 * @since 2022/3/10 19:04
 */
@Slf4j
public class LeaderElection {
    public static void main(String[] args) throws InterruptedException {
        CLIENT.start();
        var ls = new LeaderSelector(CLIENT, LEADER_ELECTION_PATH, new LeaderSelectorListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                if (connectionState == ConnectionState.SUSPENDED || connectionState == ConnectionState.LOST) {
                    throw new RuntimeException();
                }
            }

            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                log.info("成为Leader");
                Thread.sleep(10000);
                log.info("失去leader权限");
            }
        });
        ls.autoRequeue();
        ls.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
