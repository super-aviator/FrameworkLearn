package com.xqk.learn.springboot.zookeeper.demo.master_follower;

import com.xqk.learn.springboot.zookeeper.demo.master_follower.watcher.LogWatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.time.Instant;

/**
 * @author xiongqiankun
 * @since 2021/10/16 15:39
 */
@Slf4j
public class ClientAdmin {
    private final ZooKeeper zooKeeper;

    public ClientAdmin(String connectString, int sessionTimeout, Watcher watcher) throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ClientAdmin clientAdmin = new ClientAdmin("49.235.67.7:2181,49.235.67.7:2182,49.235.67.7:2183", 130000, new LogWatcher());
        clientAdmin.listState();
        clientAdmin.close();
    }

    public void listState() {
        org.apache.zookeeper.data.Stat stat = new org.apache.zookeeper.data.Stat();
        String whiteSpace = " ";
        try {
            System.out.println("Master");
            zooKeeper.getData("/master", false, stat);
            System.out.print("master  创建时间：" + Instant.ofEpochMilli(stat.getCtime()));
        } catch (KeeperException e) {
            if (e instanceof KeeperException.NoNodeException) {
                System.out.print(whiteSpace + "/master节点不存在");
            }
        } catch (Exception e) {
            log.error("", e);
        }
        System.out.println();

        System.out.println("Workers");
        try {
            for (String children : zooKeeper.getChildren("/workers", false)) {
                byte[] bytes = zooKeeper.getData("/workers/" + children, false, stat);
                String data = new String(bytes);
                System.out.println(whiteSpace + children + "  " + data);
            }
        } catch (Exception e) {
            log.error("", e);
        }


        System.out.println("Tasks");
        try {
            for (String children : zooKeeper.getChildren("/tasks", false)) {
                byte[] bytes = zooKeeper.getData("/tasks/" + children, false, stat);
                String data = new String(bytes);
                System.out.println(whiteSpace + children + "  " + data);
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}
