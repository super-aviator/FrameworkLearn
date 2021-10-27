package com.xqk.learn.springboot.zookeeper.demo.master_follower;

import com.xqk.learn.springboot.zookeeper.demo.master_follower.constant.ZookeeperConstant;
import com.xqk.learn.springboot.zookeeper.demo.master_follower.watcher.LogWatcher;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * @author xiongqiankun
 * @since 2021/10/15 13:41
 */
@Slf4j
@Getter
public class Master {
    public final String masterPath = "/master";
    private final ZooKeeper zookeeper;
    private final int id;
    private boolean isLeader;
    private MasterStatus status;

    public Master(String connectString, int sessionTimeout, Watcher watcher) throws IOException {
        zookeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
        id = new Random().nextInt();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // Master master = new Master(ZookeeperConstant.connectString, ZookeeperConstant.sessionTimeout, new LogWatcher());
        // master.runForMaster();
        // // log.info(master.toString());
        // if (master.isLeader) {
        //     log.info("我是首领");
        // } else {
        //     log.info("我不是首领");
        // }
        // master.close();

        Master master = new Master(ZookeeperConstant.connectString, ZookeeperConstant.sessionTimeout, new LogWatcher());
        master.runForMasterAsync();
        Thread.sleep(300000);
        master.close();
    }

    public void masterExistsAsync() {
        AsyncCallback.StatCallback masterExistsCallback = (rc, path, ctx, stat)->{
            switch (KeeperException.Code.get(rc)) {
                case OK:
                    status = MasterStatus.ELECTED;
                    break;
                case NODEEXISTS:
                    status = MasterStatus.NOT_ELECTED;
                    break;
                case CONNECTIONLOSS:
                    checkMaster();
                    break;
                default:
                    status = MasterStatus.NOT_ELECTED;
                    break;
            }
        };

        Watcher masterExistsWatcher = event->{
            if (event.getType() == Watcher.Event.EventType.NodeDeleted) {
                if (masterPath.equals(event.getPath())) {
                    runForMasterAsync();
                }
            }
        };

        zookeeper.exists(masterPath, masterExistsWatcher, masterExistsCallback, null);
    }

    /**
     * 尝试创建主节点
     */
    public void runForMaster() {
        while (true) {
            try {
                zookeeper.create(masterPath, String.valueOf(id).getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (InterruptedException e) {
                log.error("[{}]", e.getMessage());
                break;
            } catch (KeeperException e) {
                log.error("[{}]", e.getMessage());
                if (e instanceof KeeperException.ConnectionLossException) {
                } else if (e instanceof KeeperException.NodeExistsException) {
                    isLeader = false;
                    break;
                } else {
                    break;
                }
            }
            checkMaster();
        }
    }

    /**
     * 异步创建开放权限的Node
     */
    public void runForMasterAsync() {
        zookeeper.create(masterPath, String.valueOf(id).getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, (rc, savedPath, ctx, name)->{
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    checkMasterAsync();
                    break;
                case OK:
                    isLeader = true;
                    break;
                default:
                    isLeader = false;
            }
            log.info(this.toString());
        }, null);
    }

    /**
     * 检查当前节点是否是某路径的首领节点
     */
    private void checkMaster() {
        while (true) {
            Stat stat = new Stat();
            try {
                byte[] data = zookeeper.getData(masterPath, false, stat);
                isLeader = Integer.parseInt(new String(data)) == this.id;
                break;
            } catch (InterruptedException e) {
                log.error("", e);
                break;
            } catch (KeeperException e) {
                log.error("", e);
                if (e instanceof KeeperException.ConnectionLossException) {
                    //如果是ConnectionLossException异常，则继续while循环
                } else if (e instanceof KeeperException.NoNodeException) {
                    //如果没有主节点，则继续创建
                    runForMaster();
                    break;
                }
            }
        }
    }

    /**
     * 检查当前节点是否是某路径的首领节点
     *
     * @return 是否是路径的首领节点
     */
    private void checkMasterAsync() {
        zookeeper.getData(masterPath, false, (rc, savedPath, ctx, data, stat)->{
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    checkMasterAsync();
                    break;
                case NONODE:
                    runForMasterAsync();
                    break;
            }
            this.isLeader = data != null && (Integer.parseInt(new String(data)) == this.id);
            log.info(this.toString());
        }, null);
    }

    public void close() throws InterruptedException {
        zookeeper.close();
    }

    public boolean isLeader() {
        return isLeader;
    }

    @Override
    public String toString() {
        return "ZK{isLeader=" + isLeader + ", zookeeper=" + zookeeper + ", id=" + id + "}";
    }

    public enum MasterStatus {
        ELECTED, NOT_ELECTED;
    }
}
