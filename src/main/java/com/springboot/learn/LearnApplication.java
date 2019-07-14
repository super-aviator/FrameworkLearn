package com.springboot.learn;

import com.springboot.learn.web.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Aviator
 */
@SpringBootApplication
public class LearnApplication {

    public static void main(String[] args) {
//        SpringApplication springApplication=new SpringApplication();
//        springApplication.setSources(new HashSet<>(Arrays.asList("com.springboot.learn")));
//        springApplication.setBannerMode(Banner.Mode.OFF);
//        springApplication.run(args);

        SpringApplication.run(LearnApplication.class, args);
    }

    /**
     * 特定类型的RedisTemplate需要自己创建相应的bean才行。
     *
     * @param redisConnectionFactory redis连接池
     * @return redis模板
     */
    @Bean
    public RedisTemplate<String, User> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置key的序列化器，非常重要
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
