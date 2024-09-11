package com.xqk.lean.framework.statemachine.demo.config;

import com.xqk.learn.framework.statemachine.demo.event.Events;
import com.xqk.learn.framework.statemachine.demo.state.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.StateDoActionPolicy;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * DemoStateMachineFactory
 *
 * @author qiankun.xiong
 * @since 2024/9/8 17:28
 */
@Slf4j
@Configuration
@EnableStateMachineFactory(contextEvents = false, name = "xqk-state-machine-factory")
public class DemoStateMachineFactory extends StateMachineConfigurerAdapter<States, Events> {
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
              // .autoStartup(true)
              .stateDoActionPolicy(StateDoActionPolicy.TIMEOUT_CANCEL)
              .stateDoActionPolicyTimeout(10, TimeUnit.SECONDS)
              .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
              .initial(States.SI)
              .end(States.S3)
              .states(EnumSet.allOf(States.class));

    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.SI)
                .target(States.S1)
                .event(Events.E1)
                .and()
                .withExternal()
                .source(States.S1)
                .target(States.S2)
                .event(Events.E2);
    }

    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                log.info("state change to " + to.getId());
            }
        };
    }
}
