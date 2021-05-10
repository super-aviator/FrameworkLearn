package com.xqk.learn.springboot.transaction.programming;

import com.xqk.learn.springboot.transaction.dao.OutsideDAO;
import com.xqk.learn.springboot.transaction.entity.OutsideEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.TransactionManager;

/**
 * @author 熊乾坤
 * @since 2021-05-08 19:54
 */
@Component
public class ProgrammingTransactionManager {
    private final TransactionTemplate transactionTemplate;
    private final PlatformTransactionManager transactionManager;
    private final OutsideDAO outsideDAO;

    public ProgrammingTransactionManager(TransactionTemplate transactionTemplate,
                                         PlatformTransactionManager transactionManager,
                                         OutsideDAO outsideDAO) {
        this.transactionTemplate = transactionTemplate;
        this.transactionManager = transactionManager;
        this.outsideDAO = outsideDAO;
    }

    /**
     * 使用TransactionTemplate
     */
    public void saveTransactionTemplate() {
        transactionTemplate.execute((status)->{
            try {
                OutsideEntity outsideEntity = new OutsideEntity();
                outsideEntity.setName("熊乾坤");
                outsideDAO.save(outsideEntity);
                int i = 1 / 0;
                //业务代码
            } catch (Exception e) {
                //回滚
                status.setRollbackOnly();
            }
            return null;
        });
    }

    /**
     * 使用TransactionManager
     */
    public void saveTransactionManager() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            OutsideEntity outsideEntity = new OutsideEntity();
            outsideEntity.setName("熊乾坤");
            outsideDAO.save(outsideEntity);
            int i = 1 / 0;
            //业务代码
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }
}
