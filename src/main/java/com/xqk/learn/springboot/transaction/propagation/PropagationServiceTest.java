package com.xqk.learn.springboot.transaction.propagation;

import com.xqk.learn.springboot.LearnApplication;
import com.xqk.learn.springboot.transaction.dao.InsideDAO;
import com.xqk.learn.springboot.transaction.dao.OutsideDAO;
import com.xqk.learn.springboot.transaction.entity.InsideEntity;
import com.xqk.learn.springboot.transaction.entity.OutsideEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * @author 熊乾坤
 * @since 2021-05-06 19:49
 */
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

    @Test
    public void supportsTest() {
        propagationService.outSideNotTInsideWith_SUPPORT(outsideEntity, insideEntity, true);
        Assert.isTrue(outsideDAO.findById(1L)
                                .isPresent(), "outsideEntity 不存在");
        ;
        Assert.isTrue(!insideDAO.findById(1L)
                                .isPresent(), "insideEntity 存在");
        ;
    }
}
