package com.xqk.lean.framework.statemachine.tcloan.loan.config;

import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanEvents;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.stereotype.Component;

/**
 * 状态机构建Service
 *
 * @author qiankun.xiong
 * @since 2024/9/10 15:02
 */
@Slf4j
@Component
public class LoanStateMachineConfigService {
    @Autowired
    private StateMachineRuntimePersister<LoanStates, LoanEvents, String> stateMachineRuntimePersister;


    /**
     * 借款状态机Builder
     */
    public StateMachineBuilder.Builder<LoanStates, LoanEvents> getLoanStateMachineBuilder(String machineId) throws Exception {
        var builder = StateMachineBuilder.<LoanStates, LoanEvents>builder();
        // builder.configureConfiguration()
        //        .withPersistence()
        //        .runtimePersister(stateMachineRuntimePersister);

        builder.configureConfiguration()
               .withConfiguration()
               .machineId(machineId);

        builder.configureStates()
               .withStates()
               .initial(LoanStates.I000)
               .state(LoanStates.I005)
               .state(LoanStates.I010)
               .state(LoanStates.I015);

        builder.configureTransitions()
               .withExternal()
               .source(LoanStates.I000)
               .target(LoanStates.I005)
               .event(LoanEvents.APPLY)
               .and()
               .withExternal()
               .source(LoanStates.I005)
               .target(LoanStates.I010)
               .and()
               .withExternal()
               .source(LoanStates.I010)
               .target(LoanStates.I015)
               .event(LoanEvents.LOAN_PRE_APPLY_RESULT);
        return builder;
    }

    /**
     * 还款状态机Builder
     */
    public StateMachineBuilder getRepayStateMachineBuilder(String machineId) throws Exception {
        var builder = StateMachineBuilder.builder();
        builder.configureConfiguration()
               .withConfiguration()
               .machineId(machineId);
        return null;
    }

    /**
     * 资方还款回调状态机Builder
     */
    public StateMachineBuilder getRepayNotifyStateMachineBuilder(String machineId) {
        return null;
    }
}
