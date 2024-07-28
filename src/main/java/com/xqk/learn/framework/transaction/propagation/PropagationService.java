package com.xqk.learn.framework.transaction.propagation;

import com.xqk.learn.framework.transaction.dao.InsideDAO;
import com.xqk.learn.framework.transaction.dao.OutsideDAO;
import com.xqk.learn.framework.transaction.entity.InsideEntity;
import com.xqk.learn.framework.transaction.entity.OutsideEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 熊乾坤
 * @since 2021-05-06 19:33
 */
@Slf4j
@Component
public class PropagationService {

    private final OutsideDAO outsideDAO;
    private final InsideDAO insideDAO;
    /**
     * 使用自我注入的方法解决内部方法调用产生事务不生效的问题
     */

    public PropagationService(OutsideDAO outsideDAO, InsideDAO insideDAO) {
        this.outsideDAO = outsideDAO;
        this.insideDAO = insideDAO;
    }

    /* ------------------------------------- OUTSIDE -------------------------------- */

    public void outSideWithoutT(OutsideEntity outsideEntity, InsideEntity insideEntity, boolean insideException, boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).insideWithoutT(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    public void outSideWithoutTInsideWith_SUPPORT(OutsideEntity outsideEntity,
                                                  InsideEntity insideEntity,
                                                  boolean insideException,
                                                  boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_SUPPORTS(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    public void outSideWithoutTInsideWith_REQUIRES_NEW(OutsideEntity outsideEntity,
                                                       InsideEntity insideEntity,
                                                       boolean insideException,
                                                       boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_REQUIRES_NEW(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithTInsideWith_REQUIRES_NEW(OutsideEntity outsideEntity,
                                                    InsideEntity insideEntity,
                                                    boolean insideException,
                                                    boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_REQUIRES_NEW(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    /* ------------------------------------- REQUIRED -------------------------------- */
    public void outSideWithoutTInsideWith_REQUIRED(OutsideEntity outsideEntity,
                                                   InsideEntity insideEntity,
                                                   boolean insideException,
                                                   boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_REQUIRED(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithTInsideWith_REQUIRED(OutsideEntity outsideEntity,
                                                InsideEntity insideEntity,
                                                boolean insideException,
                                                boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_REQUIRED(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithTAndCaptureInsideWith_REQUIRED(OutsideEntity outsideEntity,
                                                          InsideEntity insideEntity,
                                                          boolean insideException,
                                                          boolean captureInsideException,
                                                          boolean outsideException) {
        outsideDAO.save(outsideEntity);
        if (captureInsideException) {
            try {
                ((PropagationService) AopContext.currentProxy()).inside_REQUIRED(insideEntity, insideException);
            } catch (Exception ignored) {
            }
        } else {
            ((PropagationService) AopContext.currentProxy()).inside_REQUIRED(insideEntity, insideException);
        }
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    /* ------------------------------------- SUPPORTS -------------------------------- */
    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithTInsideWith_SUPPORTS(OutsideEntity outsideEntity,
                                                InsideEntity insideEntity,
                                                boolean insideException,
                                                boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_SUPPORTS(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    /* ------------------------------------- NOT_SUPPORTED -------------------------------- */
    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithTInsideWith_NOT_SUPPORTED(OutsideEntity outsideEntity,
                                                     InsideEntity insideEntity,
                                                     boolean insideException,
                                                     boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_NOT_SUPPORTED(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    public void outSideWithoutTInsideWith_NOT_SUPPORTED(OutsideEntity outsideEntity,
                                                        InsideEntity insideEntity,
                                                        boolean insideException,
                                                        boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_NOT_SUPPORTED(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    /* ------------------------------------- MANDATORY -------------------------------- */
    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithTInsideWith_MANDATORY(OutsideEntity outsideEntity,
                                                 InsideEntity insideEntity,
                                                 boolean insideException,
                                                 boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_MANDATORY(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    public void outSideWithoutTInsideWith_MANDATORY(OutsideEntity outsideEntity,
                                                    InsideEntity insideEntity,
                                                    boolean insideException,
                                                    boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_MANDATORY(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithT(OutsideEntity outsideEntity, InsideEntity insideEntity, boolean insideException, boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).insideWithoutT(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    /* ------------------------------------- NEVER -------------------------------- */

    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithTInsideWith_NEVER(OutsideEntity outsideEntity,
                                             InsideEntity insideEntity,
                                             boolean insideException,
                                             boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_NEVER(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    public void outSideWithoutTInsideWith_NEVER(OutsideEntity outsideEntity,
                                                InsideEntity insideEntity,
                                                boolean insideException,
                                                boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).inside_NEVER(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    /* ------------------------------------- NESTED -------------------------------- */
    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithTInsideWith_NESTED(OutsideEntity outsideEntity,
                                              InsideEntity insideEntity,
                                              boolean insideException,
                                              boolean outsideException) {
        outsideDAO.save(outsideEntity);
        ((PropagationService) AopContext.currentProxy()).insideWith_NESTED(insideEntity, insideException);
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void outSideWithTAndCaptureInsideWith_NESTED(OutsideEntity outsideEntity,
                                                        InsideEntity insideEntity,
                                                        boolean insideException,
                                                        boolean captureInsideException,
                                                        boolean outsideException) {
        outsideDAO.save(outsideEntity);
        if (captureInsideException) {
            try {
                ((PropagationService) AopContext.currentProxy()).insideWith_NESTED(insideEntity, insideException);
            } catch (Exception e) {
                log.error("", e);
            }
        } else {
            ((PropagationService) AopContext.currentProxy()).insideWith_NESTED(insideEntity, insideException);
        }
        if (outsideException) {
            throw new RuntimeException();
        }
    }

    //@Transactional(propagation = Propagation.REQUIRED)
    //public void outSideWithTInsideWith_NESTED(OutsideEntity outsideEntity, InsideEntity insideEntity, boolean insideException,
    //                                          boolean outsideException) {
    //    outsideDAO.save(outsideEntity);
    //    propagationService.insideWith_NESTED(insideEntity, insideException);
    //    if(outsideException){
    //        throw new RuntimeException();
    //    }
    //}


    /* ------------------------------------- INSIDE -------------------------------- */

    public void insideWithoutT(InsideEntity entity, boolean exception) {
        insideDAO.save(entity);
        if (exception) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void inside_REQUIRED(InsideEntity entity, boolean exception) {
        insideDAO.save(entity);
        if (exception) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void inside_REQUIRES_NEW(InsideEntity entity, boolean exception) {
        insideDAO.save(entity);
        if (exception) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void inside_SUPPORTS(InsideEntity entity, boolean exception) {
        insideDAO.save(entity);
        if (exception) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void inside_NOT_SUPPORTED(InsideEntity entity, boolean exception) {
        insideDAO.save(entity);
        if (exception) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void inside_MANDATORY(InsideEntity entity, boolean exception) {
        insideDAO.save(entity);
        if (exception) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.NEVER)
    public void inside_NEVER(InsideEntity entity, boolean exception) {
        insideDAO.save(entity);
        if (exception) {
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insideWith_NESTED(InsideEntity entity, boolean exception) {
        insideDAO.save(entity);
        if (exception) {
            throw new RuntimeException();
        }
    }
}
