package com.xqk.learn.framework.transaction.programming;

import com.xqk.learn.framework.transaction.dao.OutsideDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 熊乾坤
 * @since 2021-05-08 19:54
 */
@Slf4j
@SpringBootTest

public class ProgrammingTransactionManagerTest {
    @Autowired
    private static OutsideDAO outsideDAO;

    @Autowired
    private ProgrammingTransactionManager programmingTransactionManager;


    @BeforeAll
    public static void clearTable() {
        outsideDAO.deleteAll();
    }

    @AfterAll
    public static void search() {
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
