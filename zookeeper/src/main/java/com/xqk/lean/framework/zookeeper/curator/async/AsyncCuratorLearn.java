package com.xqk.lean.framework.zookeeper.curator.async;

import com.netflix.curator.framework.recipes.cache.NodeCache;
import com.netflix.curator.framework.recipes.cache.PathChildrenCache;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.xqk.learn.framework.zookeeper.common.ZookeeperConstant.AUTH;
import static com.xqk.learn.framework.zookeeper.common.ZookeeperConstant.CLIENT;

/**
 * CuratorLearn
 * Curator异步API学习
 *
 * @author xiongqiankun
 * @since 2022/3/10 16:50
 */
@Slf4j
public class AsyncCuratorLearn {

    public static void main(String[] args) throws Exception {
        // watchPathDataChange();
        // connectionStateListener();
        // watchPathChildrenChange();
        createNodeWithAcl("/auth1");
        create("/auth/child");

    }

    public static void connectionStateListener() {
        CLIENT.getConnectionStateListenable().addListener((client, newState) -> {
            // 这里我们可以针对不同的连接状态进行特殊的处理
            switch (newState) {
                case CONNECTED:
                    // 第一次成功连接到ZooKeeper之后会进入该状态，对于每个CuratorFramework对象，此状态仅出现一次
                    log.info("链接建立");
                    break;
                case SUSPENDED:
                    // ZooKeeper的连接丢失
                    log.info("链接丢失");
                    break;
                case RECONNECTED:
                    // 丢失的连接被重新建立
                    log.info("重连");
                    break;
                case LOST:
                    // 当Curator认为会话已经过期时，则进入此状态
                    log.info("会话过期");
                    break;
                case READ_ONLY:
                    // 连接进入只读模式
                    log.info("只读模式");
                    break;
                default:
                    break;
            }
        });
    }

    @SneakyThrows
    public static void createNodeWithAcl(String path) {
        CLIENT.start();
        CLIENT.getZookeeperClient().blockUntilConnectedOrTimedOut();

        List<ACL> aclList = new ArrayList<>();
        // 对密码进行加密
        String digest1 = DigestAuthenticationProvider.generateDigest("heibai:123456");
        String digest2 = DigestAuthenticationProvider.generateDigest(AUTH);
        Id user01 = new Id("digest", digest1);
        Id user02 = new Id("digest", digest2);
        // 指定所有权限
        aclList.add(new ACL(ZooDefs.Perms.ALL, user01));
        // 如果想要指定权限的组合，中间需要使用 | ,这里的|代表的是位运算中的 按位或
        aclList.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.READ, user02));

        // 创建节点
        CLIENT.create()
              .creatingParentsIfNeeded()
              .withACL(aclList)
              // .withMode(CreateMode.PERSISTENT)
              .forPath(path);
    }

    /**
     * NodeCache能够在节点数据变动和节点不存在时触发监听
     * 但是节点删除时无法触发
     */
    @SneakyThrows
    public static void watchPathDataChange() {
        CLIENT.start();
        //等待直到链接建立
        CLIENT.getZookeeperClient().blockUntilConnectedOrTimedOut();
        var nodeCache = new NodeCache(CLIENT, "/zookeeper/abc");
        nodeCache.start(true);
        nodeCache.getListenable().addListener(() -> {
            log.info("current data is {}", new String(nodeCache.getCurrentData().getData()));
        });
        CLIENT.setData().forPath("/zookeeper/abc", "done".getBytes(StandardCharsets.UTF_8));
        Thread.sleep(10000);

    }

    /**
     * PathChildrenCache只能监听设定的path的一级目录的变动，无法监听二级目录的变动
     */
    @SneakyThrows
    public static void watchPathChildrenChange() {
        CLIENT.start();
        //等待直到链接建立
        CLIENT.getZookeeperClient().blockUntilConnectedOrTimedOut();
        var nodeCache = new PathChildrenCache(CLIENT, "/zookeeper/parent/child", true);
        nodeCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        nodeCache.getListenable().addListener((client, event) -> {
            switch ((event.getType())) {
                case CHILD_ADDED:
                    log.info("childAdded {}", event.getData().getPath());
                    log.info("current now data is {}", new String(event.getData().getData()));
                    break;
                case CHILD_UPDATED:
                    log.info("childUpdate {}", event.getData().getPath());
                    log.info("current now data is {}", new String(event.getData().getData()));
                    break;
                case CHILD_REMOVED:
                    log.info("childRemove {}", event.getData().getPath());
                    break;
                default:
                    break;
            }
        });
        try {
            CLIENT.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/zookeeper/parent/child",
                    "done1".getBytes(StandardCharsets.UTF_8));
            //节点已存在
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        CLIENT.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/zookeeper/parent/child/son",
                "done1".getBytes(StandardCharsets.UTF_8));
        Thread.sleep(1000);
        CLIENT.setData().forPath("/zookeeper/parent/child/son",
                "done2".getBytes(StandardCharsets.UTF_8));
        Thread.sleep(1000);
        CLIENT.delete().forPath("/zookeeper/parent/child/son");
        Thread.sleep(1000);
    }

    @SneakyThrows
    public static void create(String path) {
        // CLIENT.start();
        //等待直到链接建立
        // CLIENT.getZookeeperClient().blockUntilConnectedOrTimedOut();
        CLIENT.create()
              .creatingParentsIfNeeded()
              .inBackground((curatorFramework, curatorEvent) ->
                      log.info("Type：{}，Code：{}", curatorEvent.getType().name(), curatorEvent.getResultCode()))
              .forPath(path);
    }
}
