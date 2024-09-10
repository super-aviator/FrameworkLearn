package com.xqk.learn.framework.statemachine.tcloan.loan.config;

import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanEvents;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanStates;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

/**
 * 状态机构建Service
 *
 * @author qiankun.xiong
 * @since 2024/9/10 15:02
 */
@Component
public class LoanStateMachineConfigService implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 借款状态机Builder
     */
    public StateMachineBuilder.Builder<LoanStates, LoanEvents> getLoanStateMachineBuilder(String machineId) throws Exception {
        var builder = StateMachineBuilder.<LoanStates, LoanEvents>builder();
        builder.configureConfiguration()
               .withConfiguration()
               .beanFactory(applicationContext)
               .machineId(machineId);

        builder.configureStates()
               .withStates()
               .state(LoanStates.I000)
               .state(LoanStates.I005);

        builder.configureTransitions()
               .withExternal()
               .source(LoanStates.I000)
               .target(LoanStates.I005)
               .event(LoanEvents.APPLY);
        return builder;
    }

    /**
     * 还款状态机Builder
     */
    public StateMachineBuilder getRepayStateMachineBuilder(String machineId) throws Exception {
        var builder = StateMachineBuilder.builder();
        builder.configureConfiguration()
               .withConfiguration()
               .beanFactory(applicationContext)
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
