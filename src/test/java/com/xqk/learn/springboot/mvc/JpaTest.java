package com.xqk.learn.springboot.mvc;

import com.xqk.learn.springboot.data.jpa.repository.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class JpaTest {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    public void getPrimaryResultTest() {
//        boolean b=userJpaRepository.exists(123456);
//        log.info()
    }
}
