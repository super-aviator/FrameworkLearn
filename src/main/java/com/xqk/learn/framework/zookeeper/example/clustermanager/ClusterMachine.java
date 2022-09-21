package com.xqk.learn.framework.zookeeper.example.clustermanager;

/**
 * 定义集群中节点行为的抽象
 *
 * @author Aviator
 */
public interface ClusterMachine {
    /**
     * 启动节点时需要做的操作
     */
    void start();

    /**
     * 停止节点时需要做的操作
     */
    void stop();
}
