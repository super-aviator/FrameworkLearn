package com.xqk.learn.framework.mybatis.dao;

import com.xqk.learn.framework.mybatis.mapper.UserMapper;
import com.xqk.learn.framework.springboot.data.jpa.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
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
        Assertions.assertNotSame(beforeUser, afterUser);

        afterUser.setName("熊乾坤" + new Random().ints(0, 100000000).limit(1).findFirst().orElse(9999));
        userMapper.updateByPrimaryKey(afterUser);
        afterUser = userMapper.selectByPrimaryKey(103);
        Assertions.assertEquals(beforeUser, afterUser);
        User tempUser = userMapper.selectByPrimaryKey(103);
    }
}