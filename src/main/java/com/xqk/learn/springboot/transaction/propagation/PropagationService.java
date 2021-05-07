package com.xqk.learn.springboot.transaction.propagation;

import com.xqk.learn.springboot.transaction.dao.InsideDAO;
import com.xqk.learn.springboot.transaction.dao.OutsideDAO;
import com.xqk.learn.springboot.transaction.entity.InsideEntity;
import com.xqk.learn.springboot.transaction.entity.OutsideEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 熊乾坤
 * @since 2021-05-06 19:33
 */
@Component
public class PropagationService {

    private final OutsideDAO outsideDAO;
    private final InsideDAO insideDAO;
    /**
     * 使用自我注入的方法解决内部方法调用产生事务不生效的问题
     */
    @Autowired
    PropagationService propagationService;

    public PropagationService(OutsideDAO outsideDAO, InsideDAO insideDAO) {
        this.outsideDAO = outsideDAO;
        this.insideDAO = insideDAO;
    }

    /* ------------------------------------- OUTSIDE -------------------------------- */

    public void outSideNotT(OutsideEntity outsideEntity, InsideEntity insideEntity, boolean insideException, boolean outsideException) {
        outsideDAO.save(outsideEntity);
        propagationService.insideNotT(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    public void outSideNotTInsideWith_SUPPORT(OutsideEntity outsideEntity,
                                              InsideEntity insideEntity,
                                              boolean insideException,
                                              boolean outsideException) {
        outsideDAO.save(outsideEntity);
        propagationService.inside_SUPPORTS(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithT(OutsideEntity outsideEntity, InsideEntity insideEntity, boolean exception) {
        outsideDAO.save(outsideEntity);
        propagationService.insideNotT(insideEntity, exception);
    }

    /* ------------------------------------- INSIDE -------------------------------- */

    public void insideNotT(InsideEntity entity, boolean exception) {
        if (exception) {
            throw new RuntimeException();
        }
        insideDAO.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void inside_REQUIRED(InsideEntity entity, boolean exception) {
        if (exception) {
            throw new RuntimeException();
        }
        insideDAO.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void inside_REQUIRES_NEW(InsideEntity entity, boolean exception) {
        if (exception) {
            throw new RuntimeException();
        }
        insideDAO.save(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void inside_SUPPORTS(InsideEntity entity, boolean exception) {
        if (exception) {
            throw new RuntimeException();
        }
        insideDAO.save(entity);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void inside_NOT_SUPPORTED(InsideEntity entity, boolean exception) {
        if (exception) {
            throw new RuntimeException();
        }
        insideDAO.save(entity);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void inside_MANDATORY(InsideEntity entity, boolean exception) {
        if (exception) {
            throw new RuntimeException();
        }
        insideDAO.save(entity);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void inside_NEVER(InsideEntity entity, boolean exception) {
        if (exception) {
            throw new RuntimeException();
        }
        insideDAO.save(entity);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insideWith_NESTED(InsideEntity entity, boolean exception) {
        if (exception) {
            throw new RuntimeException();
        }
        insideDAO.save(entity);
    }
}
