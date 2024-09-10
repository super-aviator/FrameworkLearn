package com.xqk.learn.framework.statemachine.tcloan.loan;

import com.xqk.learn.framework.statemachine.tcloan.consts.StateMachineConst;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanEvents;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * CommandLineRunner
 *
 * @author qiankun.xiong
 * @since 2024/7/7 13:36
 */
@Component
public class LoanStateMachineCommandLineRunner implements CommandLineRunner {
    @Autowired
    @Qualifier(StateMachineConst.LOAN_STATE_MACHINE)
    private StateMachine<LoanStates, LoanEvents> loanStateMachine;

    @Override
    public void run(String... args) throws Exception {
        loanStateMachine.startReactively().subscribe();
        loanStateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(LoanEvents.APPLY).build())).subscribe();
    }
}
