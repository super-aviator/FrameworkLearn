package com.xqk.learn.framework.statemachine.tcloan.loan.context;

import com.xqk.learn.framework.statemachine.tcloan.consts.StateMachineConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * LoanStateMachineContext
 *
 * @author qiankun.xiong
 * @since 2024/9/10 15:44
 */
@Slf4j
@WithStateMachine(id = StateMachineConst.LOAN_STATE_MACHINE)
public class LoanStateMachineContext {
    @OnTransition(source = "I000", target = "I005")
    public void fromS1ToS2() {
        log.info("source:I000,target:I005");
    }
}
