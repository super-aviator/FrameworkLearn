package com.xqk.lean.framework.zookeeper.master_wroker;

import com.xqk.lean.framework.zookeeper.master_wroker.constant.ZookeeperConstant;
import com.xqk.lean.framework.zookeeper.master_wroker.watcher.LogWatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author xiongqiankun
 * @since 2021/10/16 14:54
 */
@Slf4j
public class Client {
    private final ZooKeeper zooKeeper;

    public Client(String connectString, int sessionTimeout, Watcher watcher) throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client(ZookeeperConstant.connectString, ZookeeperConstant.sessionTimeout, new LogWatcher());
        log.info("任务[{}]创建成功", client.queueCommand("cmd"));
        client.close();
    }

    public String queueCommand(String command) throws InterruptedException {
        String taskName;
        while (true) {
            try {
                taskName = zooKeeper.create("/tasks/task-", command.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
                break;
            } catch (KeeperException e) {
                log.error("", e);
                if (e instanceof KeeperException.ConnectionLossException) {
                    continue;
                }
                throw new RuntimeException(e);
            }
        }
        return taskName;
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}