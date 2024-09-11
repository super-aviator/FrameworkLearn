package com.xqk.learn.framework.statemachine.tcloan.loan.context;

import com.xqk.learn.framework.statemachine.tcloan.annotations.LoanOnStateEntry;
import com.xqk.learn.framework.statemachine.tcloan.consts.StateMachineConst;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanEvents;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.EnableWithStateMachine;

/**
 * LoanStateMachineContext
 *
 * @author qiankun.xiong
 * @since 2024/9/10 15:44
 */
@Slf4j
@EnableWithStateMachine
@WithStateMachine(id = StateMachineConst.LOAN_STATE_MACHINE)
public class LoanStateMachineContext {
    @LoanOnStateEntry(source = LoanStates.I000)
    public void fromS1ToS2(StateMachine<LoanStates, LoanEvents> stateMachine,
                           Message<LoanEvents> message) {
        log.info("source:I000,target:I005," + stateMachine.getId() + ":" + message.getPayload());
    }

    @OnStateChanged(source = "I005", target = "I010")
    public void fromS5ToS10(StateMachine<LoanStates, LoanEvents> stateMachine,
                            Message<LoanEvents> message) {
        log.info("source:I005,target:I010" + stateMachine.getId() + ":" + message.getPayload());
    }
}
