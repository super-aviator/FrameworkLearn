package com.xqk.learn.springboot.zookeeper.curator.tools;

import static com.xqk.learn.springboot.zookeeper.common.ZookeeperConstant.CLIENT;
import static com.xqk.learn.springboot.zookeeper.common.ZookeeperConstant.DISTRIBUTED_COUNTER;

import com.netflix.curator.framework.recipes.atomic.DistributedAtomicInteger;
import com.netflix.curator.retry.RetryNTimes;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * DistributedCounter
 *
 * @author xiongqiankun
 * @since 2022/3/11 9:30
 */
@Slf4j
public class DistributedCounter {
    @SneakyThrows
    public static void main(String[] args) {
        CLIENT.start();
        Thread.sleep(40000);
        var counter = new DistributedAtomicInteger(CLIENT, DISTRIBUTED_COUNTER, new RetryNTimes(3, 1000));
        var res = counter.add(8);
        log.info("preValue:{} result:{}", res.preValue(), res.postValue());
        res = counter.add(8);
        log.info("preValue:{} result:{}", res.preValue(), res.postValue());
    }
}
