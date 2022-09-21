package com.xqk.learn.framework.transaction.propagation;

import com.xqk.learn.framework.LearnApplication;
import com.xqk.learn.framework.transaction.dao.InsideDAO;
import com.xqk.learn.framework.transaction.dao.OutsideDAO;
import com.xqk.learn.framework.transaction.entity.InsideEntity;
import com.xqk.learn.framework.transaction.entity.OutsideEntity;
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
 * @since 2021-05-06 19:49
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearnApplication.class)
public class PropagationServiceTest {
    private final OutsideEntity outsideEntity = new OutsideEntity(1L, "OutSide");
    private final InsideEntity insideEntity = new InsideEntity(1L, "InSide");

    @Autowired
    private OutsideDAO outsideDAO;

    @Autowired
    private InsideDAO insideDAO;

    @Autowired
    private PropagationService propagationService;

    @Before
    public void clearTable() {
        outsideDAO.deleteAll();
        insideDAO.deleteAll();
    }

    @After
    public void search(){
        outsideDAO.findAll().forEach(e->log.info("outside：[{}]",e.getId()));
        insideDAO.findAll().forEach(e->log.info("inside：[{}]",e.getId()));
    }

    @Test
    public void supportsTest() {
        // propagationService.outSideNotTInsideWith_SUPPORT(outsideEntity, insideEntity, true,false);
        propagationService.outSideWithTInsideWith_SUPPORTS(outsideEntity, insideEntity, true,false);
    }

    @Test
    public void notSupportedTest() {
        // propagationService.outSideNotTInsideWith_SUPPORT(outsideEntity, insideEntity, true,false);
        propagationService.outSideWithTInsideWith_NOT_SUPPORTED(outsideEntity, insideEntity, true, false);
        //propagationService.outSideWithoutTInsideWith_NOT_SUPPORTED(outsideEntity, insideEntity, false,false);

    }

    @Test
    public void requiredTest() {
        //propagationService.outSideWithTInsideWith_REQUIRED(outsideEntity, insideEntity, true, false);
        //propagationService.outSideWithTAndCaptureInsideWith_REQUIRED(outsideEntity, insideEntity, true, true, false);
    }

    @Test
    public void requiresNewTest() {
        //propagationService.outSideWithoutTInsideWith_REQUIRES_NEW(outsideEntity, insideEntity, true, false);
        propagationService.outSideWithTInsideWith_REQUIRES_NEW(outsideEntity, insideEntity, true, false);
    }

    @Test
    public void mandatoryTest() {
        //propagationService.outSideWithTInsideWith_MANDATORY(outsideEntity, insideEntity, true, false);
        propagationService.outSideWithoutTInsideWith_MANDATORY(outsideEntity, insideEntity, true, false);
    }

    @Test
    public void neverTest() {
        //propagationService.outSideWithTInsideWith_NEVER(outsideEntity, insideEntity, true, false);
        propagationService.outSideWithoutTInsideWith_NEVER(outsideEntity, insideEntity, true, false);
    }

    @Test
    public void nestedTest() {
        // propagationService.outSideWithTInsideWith_NESTED(outsideEntity, insideEntity, true, false);
        propagationService.outSideWithTAndCaptureInsideWith_NESTED(outsideEntity, insideEntity, true, true,false);

    }
}
