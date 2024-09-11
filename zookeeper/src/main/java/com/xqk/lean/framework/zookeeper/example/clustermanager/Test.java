package com.xqk.lean.framework.zookeeper.example.clustermanager;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;

/**
 * Test
 *
 * @author xiongqiankun
 * @since 2022/3/11 14:46
 */
public class Test {
    public static void main(String[] args) {
        var executor = Executors.newFixedThreadPool(1);
        executor.submit(new Node("Node-" + LocalDateTime.now()));
        // executor.shutdown();
    }
}
