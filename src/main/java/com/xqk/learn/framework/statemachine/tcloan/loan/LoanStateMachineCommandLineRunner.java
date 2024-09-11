package com.xqk.learn.framework.statemachine.tcloan.loan;

import com.xqk.learn.framework.statemachine.tcloan.consts.StateMachineConst;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanEvents;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * CommandLineRunner
 *
 * @author qiankun.xiong
 * @since 2024/7/7 13:36
 */
@Slf4j
@Component
public class LoanStateMachineCommandLineRunner implements CommandLineRunner {
    @Autowired
    @Qualifier(StateMachineConst.LOAN_STATE_MACHINE)
    private StateMachine<LoanStates, LoanEvents> loanStateMachine;

    @Autowired
    private StateMachinePersister<LoanStates, LoanEvents, String> persister;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            try {
                loanStateMachine = persister.restore(loanStateMachine, "TQKJK123");
                loanStateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(LoanEvents.APPLY).build())).subscribe();
                log.info("{}:current state {}", Thread.currentThread().getName(), loanStateMachine.getState().getId().toString());
                persister.persist(loanStateMachine, "TQKJK123");
                loanStateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(LoanEvents.LOAN_PRE_APPLY_RESULT).build())).subscribe();
                log.info("{}:current state {}", Thread.currentThread().getName(), loanStateMachine.getState().getId().toString());
                persister.persist(loanStateMachine, "TQKJK123");
            } catch (Exception e) {
                log.info("error", e);
            }
        }).start();
        new Thread(() -> {
            try {
                loanStateMachine = persister.restore(loanStateMachine, "TQKJK1234");
                loanStateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(LoanEvents.APPLY).build())).subscribe();
                log.info("{}:current state {}", Thread.currentThread().getName(), loanStateMachine.getState().getId().toString());
                persister.persist(loanStateMachine, "TQKJK1234");
                loanStateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(LoanEvents.LOAN_PRE_APPLY_RESULT).build())).subscribe();
                log.info("{}:current state {}", Thread.currentThread().getName(), loanStateMachine.getState().getId().toString());
                persister.persist(loanStateMachine, "TQKJK1234");
            } catch (Exception e) {
                log.info("error", e);
            }
        }).start();
        new Thread(() -> {
            try {
                loanStateMachine = persister.restore(loanStateMachine, "TQKJK12345");
                loanStateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(LoanEvents.APPLY).build())).subscribe();
                log.info("{}:current state {}", Thread.currentThread().getName(), loanStateMachine.getState().getId().toString());
                persister.persist(loanStateMachine, "TQKJK12345");
                loanStateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(LoanEvents.LOAN_PRE_APPLY_RESULT).build())).subscribe();
                log.info("{}:current state {}", Thread.currentThread().getName(), loanStateMachine.getState().getId().toString());
                persister.persist(loanStateMachine, "TQKJK12345");
            } catch (Exception e) {
                log.info("error", e);
            }
        }).start();
    }
}
