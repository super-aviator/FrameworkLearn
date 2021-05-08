package com.xqk.learn.springboot.transaction.programming;

import com.xqk.learn.springboot.transaction.dao.OutsideDAO;
import com.xqk.learn.springboot.transaction.entity.OutsideEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author 熊乾坤
 * @since 2021-05-08 19:54
 */
@Component
public class ProgrammingTransactionManager {
    private final TransactionTemplate transactionTemplate;
    private final OutsideDAO outsideDAO;

    public ProgrammingTransactionManager(TransactionTemplate transactionTemplate, OutsideDAO outsideDAO) {
        this.transactionTemplate = transactionTemplate;
        this.outsideDAO = outsideDAO;
    }

    public void saveTransaction() {
        transactionTemplate.execute((status)->{
            try {
                OutsideEntity outsideEntity = new OutsideEntity();
                outsideEntity.setName("熊乾坤");
                outsideDAO.save(outsideEntity);
                //throw new RuntimeException();
                //业务代码
            } catch (Exception e) {
                //回滚
                status.setRollbackOnly();
            }
            return null;
        });
    }
}
