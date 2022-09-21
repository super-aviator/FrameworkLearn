package com.xqk.learn.framework.springboot.core.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * CacheService
 *
 * @author xiongqiankun
 * @since 2022/3/17 14:06
 */
@Slf4j
@Component
public class CacheService {
    @Cacheable("cacheMethod")
    public String cacheMethod(Long id) {
        log.info("执行了cacheMethod方法体");
        return String.valueOf(id);
    }

    @Cacheable("cacheMethodWithoutArgs")
    public String cacheMethodWithoutArgs() {
        log.info("执行了cacheMethodWithNoArgs方法体");
        return "null";
    }

    @Cacheable("cacheMethodWithoutArgsReturnNull")
    public String cacheMethodWithoutArgsReturnNull() {
        log.info("执行了cacheMethodWithoutArgsReturnNull方法体");
        return null;
    }

    /**
     * multi cache name
     * 会生成如下key:
     * multiCacheNames1:i
     * multiCacheNames2:i
     *
     * @param i key
     * @return value
     */
    @Cacheable({"multiCacheNames1", "multiCacheNames2"})
    public String multiCacheNames(Integer i) {
        log.info("执行了multiCacheNames方法体");
        return null;
    }

    /**
     * 条件缓存
     * 只有args长度大于5时才缓存
     *
     * @param args key
     * @return value
     */
    @Cacheable(value = "cacheCondition", condition = "#args.length()>5")
    public String cacheCondition(String args) {
        log.info("执行了cacheCondition方法体");
        return "cacheCondition";
    }

    /**
     * 条件缓存
     * 不同于 condition 参数，unless 参数在方法被调用后评估表达式
     * 只有args长度大于5时才缓存
     *
     * @param args key
     * @return value
     */
    @Cacheable(value = "cacheUnless", condition = "#args.length()>5", unless = "#result.length()>10")
    public String cacheUnless(String args) {
        log.info("执行了cacheUnless方法体");
        return "cacheUnless";
    }

    @CachePut(value = "cacheCondition")
    public String updateCacheCondition(String args) {
        log.info("执行了updateCacheCondition方法体");
        return "cacheUnless1111111111111111";
    }

    @CachePut(value = "spel")
    public String spel() {
        return "spel";
    }


    /**
     * 如果allEntries=true会删除cacheCondition::key开头的缓存
     * 如果为false则会删除所有cacheCondition开头的缓存
     *
     * @param key key
     */
    @CacheEvict(value = "cacheCondition", beforeInvocation = true, allEntries = true)
    public void clean(String key) {
        log.info("clean");
    }
}
