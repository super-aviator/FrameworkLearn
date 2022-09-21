package com.xqk.learn.framework.zookeeper.master_wroker;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.xqk.learn.framework.zookeeper.master_wroker.constant.ZookeeperConstant;
import com.xqk.learn.framework.zookeeper.master_wroker.enums.MasterStatus;
import com.xqk.learn.framework.zookeeper.master_wroker.watcher.LogWatcher;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiongqiankun
 * @since 2021/10/15 13:41
 */
@Slf4j
@Getter
public class Master {
    public final String masterPath = "/master";
    public final String workerPath = "/workers";
    private final ZooKeeper zookeeper;
    private final int id;
    private final Set<String> workers = new HashSet<>();
    private boolean isLeader;
    private MasterStatus status;

    public Master(String connectString, int sessionTimeout, Watcher watcher) throws IOException {
        zookeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
        id = new Random().nextInt();
    }

    /*--------------------------主节点选举--------------------------*/

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
        Thread.sleep(180000);
        master.close();
    }

    /**
     * 判断/master节点是否存在，并设置监听器
     */
    public void masterExistsAsync() {
        AsyncCallback.StatCallback masterExistsCallback = (rc, path, ctx, stat) -> {
            switch (KeeperException.Code.get(rc)) {
                case OK:
                    if (stat == null) {
                        //stat为null表示节点不存在
                        runForMasterAsync();
                    } else {
                        status = MasterStatus.ELECTED;
                    }
                    break;
                case CONNECTIONLOSS:
                    masterExistsAsync();
                    break;
                default:
                    status = MasterStatus.NOT_ELECTED;
                    break;
            }
        };

        Watcher masterExistsWatcher = event -> {
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
        zookeeper.create(masterPath, String.valueOf(id).getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, (rc, savedPath, ctx, name) -> {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    checkMasterAsync();
                    break;
                case OK:
                    isLeader = true;
                    break;
                case NODEEXISTS:
                    isLeader = false;
                    masterExistsAsync();
                    break;
                default:
                    isLeader = false;
            }
            log.info(this.toString());
            if (isLeader) {
                getWorkerList();
            }
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
    /*-----------------------------------------------------------------*/



    /*--------------------------获取子节点数据--------------------------*/

    /**
     * 检查当前节点是否是某路径的首领节点
     *
     * @return 是否是路径的首领节点
     */
    private void checkMasterAsync() {
        zookeeper.getData(masterPath, false, (rc, savedPath, ctx, data, stat) -> {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    checkMasterAsync();
                    break;
                case NONODE:
                    runForMasterAsync();
                    break;
            }
            //通过data判断是否是主节点
            this.isLeader = data != null && (Integer.parseInt(new String(data)) == this.id);
            //注册监听器
            masterExistsAsync();
            log.info(this.toString());
        }, null);
    }

    /**
     * 获取子节点并分配工作
     */
    private void getWorkerList() {
        Watcher watcher = event -> {
            if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                getWorkerList();
            }
        };
        AsyncCallback.ChildrenCallback childrenCallback = (rc, path, ctx, children) -> {
            switch (KeeperException.Code.get(rc)) {
                case OK:
                    saveAndAssignWork(children);
                    break;
                case CONNECTIONLOSS:
                    getWorkerList();
                    break;
                case NONODE:
                    zookeeper.create(workerPath, null, OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, (rc1, path1, ctx1, name1) -> {
                    }, null);
                default:
                    log.error("获取Worker异常{}", KeeperException.Code.get(rc));
            }
        };
        zookeeper.getChildren(workerPath, watcher, childrenCallback, null);
    }

    /**
     * 比对新增和失活的节点，并分配工作
     *
     * @param childrenList 新的节点列表
     */
    private void saveAndAssignWork(List<String> childrenList) {
        childrenList.forEach(worker -> {
            if (!workers.contains(worker)) {
                log.info("新增了Worker:[{}]", worker);
            }
        });
        workers.forEach(worker -> {
            if (!childrenList.contains(worker)) {
                log.info("删除了Worker:[{}]", worker);
            }
        });
        workers.clear();
        workers.addAll(childrenList);
    }
    /*-----------------------------------------------------------------*/

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
}
