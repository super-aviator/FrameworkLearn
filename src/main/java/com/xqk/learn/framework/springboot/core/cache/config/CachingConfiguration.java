package com.xqk.learn.framework.springboot.core.cache.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CachingConfig
 * Spring缓存支持
 *
 * @author xiongqiankun
 * @since 2022/3/17 14:03
 */
@Configuration
@EnableCaching
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CachingConfiguration {
    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public CacheManager getCacheManager() {
        //默认配置
        var configuration = RedisCacheConfiguration.defaultCacheConfig()
                                                   .entryTtl(Duration.ofSeconds(1000));

        //获取key值对应的过期时间
        var map = new ConcurrentHashMap<String,Duration>();
        map.put("cacheCondition", Duration.ofSeconds(100));
        map.put("spel", Duration.ofSeconds(500));

        //为每个key初始化一个缓存配置
        var cacheNames = new HashSet<String>();
        var cacheConfig = new ConcurrentHashMap<String,RedisCacheConfiguration>();
        for (Map.Entry<String,Duration> entry : map.entrySet()) {
            cacheNames.add(entry.getKey());
            cacheConfig.put(entry.getKey(), configuration.entryTtl(entry.getValue()));
        }

        //设置自定义配置
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
                                                         .cacheDefaults(configuration)
                                                         //给不同的key配置不同的过期时间
                                                         .initialCacheNames(cacheNames)
                                                         .withInitialCacheConfigurations(cacheConfig)
                                                         .build();
    }
}