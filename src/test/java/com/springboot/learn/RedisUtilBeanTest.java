package com.springboot.learn;

import com.springboot.learn.web.bean.redis.RedisUtilBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class RedisUtilBeanTest {
    @Autowired(required = false)
    RedisUtilBean redisUtilBean;

    @Test
    public void redisTest(){
        Assert.assertEquals(redisUtilBean.setString("xqk","熊乾坤"),"熊乾坤");
        Assert.assertEquals(redisUtilBean.setString("xqk","张威"),"张威");
    }
}
