package com.xqk.learn.framework.transaction.programming;

import com.xqk.learn.framework.transaction.dao.OutsideDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 熊乾坤
 * @since 2021-05-08 19:54
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProgrammingTransactionManagerTest {
    @Autowired
    private OutsideDAO outsideDAO;

    @Autowired
    private ProgrammingTransactionManager programmingTransactionManager;


    @Before
    public void clearTable() {
        outsideDAO.deleteAll();
    }

    @After
    public void search() {
        outsideDAO.findAll()
                  .forEach(e->log.info("outside：[{}]", e.getId()));
    }

    @Test
    public void testTransactionTemplateRollback() {
        programmingTransactionManager.saveTransactionTemplate();
    }

    @Test
    public void testTransactionManagerRollback() {
        programmingTransactionManager.saveTransactionManager();
    }
}
