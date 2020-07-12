package com.xqk.learn.springboot.data.jpa;

import com.xqk.learn.springboot.data.jpa.entity.TestColumnEntity;
import com.xqk.learn.springboot.data.jpa.repository.TestColumnRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * 注意，字段名不能够和数据库中的关键字重名，否则sql会报语法错误！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * 对于MySQL中的Decimal数据类型，可以使用BigDecimal或者Double、Float类型存取，会自动进行四舍五入
 *
 * @author 熊乾坤
 * @date 2020-04-16 14:39
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MysqlColumnTest {
    @Autowired
    private TestColumnRepository testColumnRepository;

    @Test
    public void testSave() {
        TestColumnEntity testColumnEntity = new TestColumnEntity();
        testColumnEntity.setBigDecimal(new BigDecimal("998.97789"));
        testColumnEntity = testColumnRepository.save(testColumnEntity);
        log.info(testColumnEntity.toString());
    }

    @Test
    public void testFind() {
        testColumnRepository.findAll().forEach(entity -> log.info(entity.toString()));
    }
}
