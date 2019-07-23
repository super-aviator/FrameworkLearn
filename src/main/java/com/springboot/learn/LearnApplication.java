package com.springboot.learn;

import com.springboot.learn.spring.data.jpa.entity.User;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collections;
import java.util.HashSet;

/**
 * The type Learn application.
 *
 * @author Aviator
 */
//@EnableWebSecurity
@SpringBootApplication
public class LearnApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

//        最常规的启动方式，使用静态方法的方式
        SpringApplication.run(LearnApplication.class, args);


//        以编程的方式激活profile，
//        SpringApplication application = new SpringApplication();
//        以变成的方式激活profile
//        application.setAdditionalProfiles("kafka");
//        以编程的方式关闭Banner
//        application.setBannerMode(Banner.Mode.OFF);
//        Source为项目中需要指定包的路径（Source不能为空）
//        application.setSources(new HashSet<>(Collections.singletonList("com.springboot.learn")));
//        application.run(args);

    }


    /**
     * 特定类型的RedisTemplate需要自己创建相应的bean进行配置。
     * 值得注意的时，设置key的序列化器非常重要，否则可能存在取出的键和存入的键不想等的情况
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