package com.xqk.lean.framework.springboot.data.jpa;

import com.xqk.learn.framework.springboot.data.jpa.entity.User;
import com.xqk.learn.framework.springboot.data.jpa.entity.UserDetail;
import com.xqk.learn.framework.springboot.data.jpa.repository.UserDetailJpaRepository;
import com.xqk.learn.framework.springboot.data.jpa.repository.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

/**
 * @author 熊乾坤
 * @since 2020-03-04 23:58
 */
@Slf4j
@SpringBootTest
public class UserEntityCascadePersistTest {
    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    UserDetailJpaRepository userDetailJpaRepository;

    @Test
    public void testCascadePersist() {
        User user = new User();
        UserDetail userDetail = new UserDetail();
        userDetail.setCredit(50f);
        userDetail.setEnrollmentDate(new Date());
        user.setName("测试级联插入");
        user.setAddress("秭归");
        user.setBirthday(new Date());
        user.setUserDetail(userDetail);
        User savedUser = userJpaRepository.save(user);
        userJpaRepository.flush();
        Optional<User> userOptional = userJpaRepository.findById(savedUser.getId());
        log.info(userOptional.get().toString());
    }

    @Test
    public void testCascadeSearchUserDetail() {
        Optional<UserDetail> userDetailOptional = userDetailJpaRepository.findById(109L);
        userDetailOptional.ifPresent(userDetail -> {
            //User user=userDetail.getUser();
        });
        log.info("完毕");
    }

    @Test
    public void testCascadeSearchUser() {
        Optional<User> userDetailOptional = userJpaRepository.findById(103L);
        userDetailOptional.ifPresent(user -> {
        });
        log.info("完毕");
    }

    @Test
    public void testCascadeMergeUpdateUser() {
        Optional<User> userOptional = userJpaRepository.findById(108L);
        userOptional.ifPresent(user -> {
            UserDetail userDetail = user.getUserDetail();
            userDetail.setCredit(10000f);
            userDetail.setEnrollmentDate(new Date());
            user.setUserDetail(userDetail);
            userJpaRepository.save(user);
        });
    }

    @Test
    public void testCascadeMergeUpdateUserDetail() {
        Optional<UserDetail> userOptional = userDetailJpaRepository.findById(109L);
        userOptional.ifPresent(userDetail -> {
            User user = userDetail.getUser();
            user.setAddress("湖北宜昌789");
            userJpaRepository.save(user);
        });
    }

    @Test
    public void testCascadeMergeInsertUser() {
        Optional<User> userOptional = userJpaRepository.findById(108L);
        userOptional.ifPresent(user -> {
            UserDetail userDetail = new UserDetail();
            userDetail.setCredit(666f);
            userDetail.setEnrollmentDate(new Date());
            user.setUserDetail(userDetail);
            userJpaRepository.save(user);
        });
    }
}
