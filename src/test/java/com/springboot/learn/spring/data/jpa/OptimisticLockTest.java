package com.springboot.learn.spring.data.jpa;

import com.springboot.learn.spring.data.jpa.entity.User;
import com.springboot.learn.spring.data.jpa.repository.UserJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OptimisticLockTest {
    @Autowired
    UserJpaRepository userJpaRepository;

    @Test
    public void optimisticLockTest() {
//        Thread thread1 = new Thread(() -> changeUsername("熊二1", 6L));
//        Thread thread2 = new Thread(() -> changeUsername("熊大1", 6L));
//        Thread thread3 = new Thread(() -> changeUsername("光头强1", 6L));
//        thread3.start();
//        thread2.start();
//        thread1.start();

        changeUsername("熊二", 6L);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changeUsername(String username, Long id) {
        User user = userJpaRepository.getOne(id);
        user.setName(username);
        userJpaRepository.saveAndFlush(user);
    }
}