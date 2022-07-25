package com.xqk.learn.springboot.zookeeper.common;

import java.nio.charset.StandardCharsets;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.retry.ExponentialBackoffRetry;

/**
 * @author xiongqiankun
 * @since 2021/10/18 13:43
 */
public interface ZookeeperConstant {
    String CONNECT_STRING = "49.235.67.7:2181,49.235.67.7:2182,49.235.67.7:2183";
    int SESSION_TIMEOUT = 20000;
    int CONNECTION_TIMEOUT = 20000;
    String CHROOT = "learn";

    String LEADER_ELECTION_PATH = "/leader";

    String DISTRIBUTED_LOCK_PATH = "/lock";

    String DISTRIBUTED_COUNTER = "/counter";

    String CLUSTER_PATH = "/cluster";

    String CLUSTER_INSTANCE_PATH = "/cluster/instance";

    String CLUSTER_MASTER_PATH = "/cluster/master";

    String AUTH = "xqk:123456";

    CuratorFramework CLIENT = CuratorFrameworkFactory.builder()
                                                     .authorization("digest", AUTH.getBytes(StandardCharsets.UTF_8))
                                                     .connectString(ZookeeperConstant.CONNECT_STRING)
                                                     .sessionTimeoutMs(ZookeeperConstant.SESSION_TIMEOUT)
                                                     .connectionTimeoutMs(ZookeeperConstant.CONNECTION_TIMEOUT)
                                                     //chroot命名空间隔离
                                                     .namespace(ZookeeperConstant.CHROOT)
                                                     .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                                                     .build();
}
