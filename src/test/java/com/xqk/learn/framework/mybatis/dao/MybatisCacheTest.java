package com.xqk.learn.framework.mybatis.dao;

import com.xqk.learn.framework.springboot.data.jpa.entity.User;
import com.xqk.learn.framework.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
//@Transactional(rollbackFor = Exception.class)
public class MybatisCacheTest {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testFirstLevelCache() {
       SqlSession sqlSession1= sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession1.getMapper(UserMapper.class);
        User beforeUser = userMapper.selectByPrimaryKey(103);

        SqlSession sqlSession2= sqlSessionFactory.openSession(true);
        User afterUser = userMapper.selectByPrimaryKey(103);
        Assert.assertNotSame(beforeUser, afterUser);

        afterUser.setName("熊乾坤" + new Random().ints(0, 100000000).limit(1).findFirst().orElse(9999));
        userMapper.updateByPrimaryKey(afterUser);
        afterUser = userMapper.selectByPrimaryKey(103);
        Assert.assertEquals(beforeUser, afterUser);
        User tempUser = userMapper.selectByPrimaryKey(103);
    }
}