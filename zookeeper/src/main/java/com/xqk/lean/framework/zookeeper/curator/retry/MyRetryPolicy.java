package com.xqk.lean.framework.zookeeper.curator.retry;


import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;

/**
 * MyRetryPolicy
 * <p>
 * 自己实现的的重试策略
 *
 * @author xiongqiankun
 * @since 2022/3/10 14:10
 */
public class MyRetryPolicy implements RetryPolicy {
    @Override
    public boolean allowRetry(int i, long l, RetrySleeper retrySleeper) {
        return false;
    }
}
