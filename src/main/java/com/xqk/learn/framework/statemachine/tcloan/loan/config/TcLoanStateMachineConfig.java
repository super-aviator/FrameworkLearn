package com.xqk.learn.framework.statemachine.tcloan.loan.config;

import com.xqk.learn.framework.statemachine.tcloan.consts.StateMachineConst;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanEvents;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

/**
 * TcLoanStateMachineConfig
 *
 * @author qiankun.xiong
 * @since 2024/9/10 14:13
 */
@Configuration
public class TcLoanStateMachineConfig {
    @Autowired
    private LoanStateMachineConfigService loanStateMachineConfigService;

    @Bean
    public StateMachine<LoanStates, LoanEvents> stateMachine() throws Exception {
        return loanStateMachineConfigService.getLoanStateMachineBuilder(StateMachineConst.LOAN_STATE_MACHINE).build();
    }
}
