package com.xqk.lean.framework.springboot.dao;

import com.xqk.learn.framework.LearnApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = LearnApplication.class)
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void testInsert() {
    }
}