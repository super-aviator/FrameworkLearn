package com.xqk.lean.framework.statemachine.tcloan.repay.config;

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
public class RepayStateMachineConfigService implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
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
    public StateMachineBuilder getRepayNotifyStateMachineBuilder(String machineId) throws Exception {
        var build = getRepayStateMachineBuilder(machineId);

        return null;
    }
}
