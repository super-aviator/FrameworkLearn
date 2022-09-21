package com.xqk.learn.framework.zookeeper.master_wroker;

import java.io.IOException;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import com.xqk.learn.framework.zookeeper.master_wroker.constant.ZookeeperConstant;
import com.xqk.learn.framework.zookeeper.master_wroker.enums.WorkerStatusEnum;
import com.xqk.learn.framework.zookeeper.master_wroker.watcher.LogWatcher;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiongqiankun
 * @since 2021/10/16 13:20
 */
@Slf4j
public class Worker {
    private final ZooKeeper zookeeper;
    private final String workerPath;
    private String id;
    private WorkerStatusEnum status;

    public Worker(String connectString, int sessionTimeout, Watcher watcher) throws IOException {
        workerPath = "/workers/worker-";
        zookeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Worker worker = new Worker(ZookeeperConstant.connectString, ZookeeperConstant.sessionTimeout, new LogWatcher());
        worker.registerAsync();
        worker.setStatus(WorkerStatusEnum.RUNNING);
        Thread.sleep(100000);
        log.info(worker.toString());
        worker.close();
    }

    public void registerAsync() {
        AsyncCallback.StringCallback registerCallback = (rc, path, ctx, name) -> {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    registerAsync();
                    break;
                case OK:
                    log.info("节点创建成功[{}]", name);
                    this.id = name.split("-")[name.split("-").length - 1];
                    break;
                case NODEEXISTS:
                    log.warn("节点已经创建[{}]", name);
                    break;
                default:
                    log.error("未知错误：" + KeeperException.create(KeeperException.Code.get(rc), path));
            }
        };

        zookeeper.create(workerPath, WorkerStatusEnum.IDLE.toString().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
                registerCallback, null);
    }

    public void getStatus() {
        AsyncCallback.DataCallback dataCallback = (rc, path, ctx, data, stat) -> {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    getStatus();
                    break;
                case OK:
                    this.status = WorkerStatusEnum.valueOf(new String(data));
                    break;
                default:
                    break;
            }
        };
        zookeeper.getData(workerPath, false, dataCallback, null);
    }

    public void setStatus(WorkerStatusEnum status) {
        this.status = status;
    }

    private void updateStatus(WorkerStatusEnum status) {
        if (status.equals(this.status)) {
            zookeeper.setData(workerPath, status.toString().getBytes(), -1, (rc, path, ctx, stat) -> {
                switch (KeeperException.Code.get(rc)) {
                    case OK:
                        log.info("状态[{}]设置成功", ctx);
                        break;
                    case CONNECTIONLOSS:
                        updateStatus((WorkerStatusEnum) ctx);
                        break;
                    default:
                        log.error("状态[{}]设置失败", ctx);
                }
            }, status);
        }
    }

    public void close() throws InterruptedException {
        zookeeper.close();
    }
}