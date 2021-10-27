package com.xqk.learn.springboot.zookeeper.demo.master_follower.watcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author xiongqiankun
 * @since 2021/10/15 13:42
 */
@Slf4j
public class LogWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
            log.info("监听到事件：[{}]", watchedEvent.getState());
    }
}
