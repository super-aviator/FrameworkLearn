package com.xqk.lean.framework.zookeeper.curator.tools;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;

import static com.xqk.lean.framework.zookeeper.common.ZookeeperConstant.CLIENT;
import static com.xqk.lean.framework.zookeeper.common.ZookeeperConstant.DISTRIBUTED_COUNTER;


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
