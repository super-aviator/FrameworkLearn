package com.xqk.learn.framework.springboot.data.redis;

import com.xqk.learn.framework.springboot.data.jpa.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Aviator
 * @since 2019-9-15
 */
@Configuration
public class MyRedisConfiguration {
    /**
     * 特定类型的RedisTemplate需要自己创建相应的bean进行配置。
     * 值得注意的时，设置key的序列化器非常重要，否则可能存在取出的键和存入的键不想等的情况
     * 一般可以使用StringRedisSerializer对键进行序列化
     *
     * @param redisConnectionFactory the redis connection factory
     * @return RedisTemplate redis template
     */
    @Bean
    @Profile("redis")
    public RedisTemplate<String, User> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        设置key的序列化器，非常重要，否则可能存在取出的键和存入的键不相等的情况
        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        设置value的序列化器,可以有序列化为多种格式
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
