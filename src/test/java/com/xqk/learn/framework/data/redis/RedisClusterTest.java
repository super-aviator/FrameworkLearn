package com.xqk.learn.framework.data.redis;

import com.xqk.learn.framework.springboot.data.jpa.common.Gender;
import com.xqk.learn.framework.springboot.data.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * RedisCluster测试
 */
@Slf4j
@SpringBootTest
public class RedisClusterTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisTemplate<String, User> userRedisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * Test redis temp with object.
     */
    @Test
    public void testRedisTempWithObject() {
        redisTemplate.opsForValue().set("iamsb", "yes");
        log.info(redisTemplate.opsForValue().get("iamsb"));

        User user = new User();
        user.setName("xqk");
        user.setAddress("HuBei");
        user.setEmail("237238@qq.com");
        user.setGender(Gender.MALE);
        userRedisTemplate.opsForValue().set("user:xqk", user);
        Assertions.assertEquals(user.toString(), Objects.requireNonNull(userRedisTemplate.opsForValue().get("user:xqk")).toString());
    }

    /**
     * Test redis temp with string.
     */
    @Test
    public void testRedisTempWithString() {
        BoundZSetOperations<String, String> opt = redisTemplate.boundZSetOps("user");
        opt.add("xqk", 1);
        opt.add("zw", 2);
        opt.add("yxm", 50);
        opt.add("cr", 10);
        Assertions.assertEquals(opt.rank("yxm"), Long.valueOf(3));
        Assertions.assertEquals(opt.range(0, 3), new HashSet<>(Arrays.asList("xqk", "zw", "yxm", "cr")));
        Assertions.assertEquals(Objects.requireNonNull(opt.rangeByScore(0, 100)).toString(), new LinkedHashSet<>(Arrays.asList("xqk", "zw", "cr", "yxm")).toString());


    }

    /**
     * 测试hash_key属性，在命令行中，对不同槽的键执行mget会抛出：
     * (error) CROSSSLOT Keys in request don't hash to the same slot
     * 可以在键中使用{}，redis会只对大括号中的值进行哈希运算，以保证大括号中的值相同的键被分配到一个槽中。
     * <p>
     * 测试证明，RedisTemplate中不存在这个问题，客户端已经帮我们处理好了这个问题
     */
    @Test
    public void testHashKey() {
        //测试hash-key
        ValueOperations<String, String> valOpt = stringRedisTemplate.opsForValue();
        valOpt.set("user1", "abc");
        valOpt.set("user2", "def");
        valOpt.set("user3", "ghi");
        valOpt.set("user4", "jkl");
        valOpt.set("user5", "mno");
        Assertions.assertEquals(valOpt.multiGet(Arrays.asList("user1", "user2", "user3", "user4", "user5")), Arrays.asList("abc", "def", "ghi", "jkl", "mno"));
    }
}
