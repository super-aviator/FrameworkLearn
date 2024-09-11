package com.xqk.lean.framework.statemachine.demo.config;

import com.xqk.learn.framework.statemachine.demo.event.Events;
import com.xqk.learn.framework.statemachine.demo.state.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.StateDoActionPolicy;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * DemoStateMachineConfig
 *
 * @author qiankun.xiong
 * @since 2024/7/6 16:23
 */
@Slf4j
@Configuration
@EnableStateMachine(name = "xqk-state-machine", contextEvents = false)
public class DemoStateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
              .autoStartup(true)
              .stateDoActionPolicy(StateDoActionPolicy.TIMEOUT_CANCEL)
              .stateDoActionPolicyTimeout(10, TimeUnit.SECONDS)
              .listener(listener())
              // .machineId("xqk-state-machine");
        ;
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        // states.withStates()
        //      .initial(States.SI)
        //      .states(EnumSet.allOf(States.class));


        // states.withStates()
        //       .initial(States.SI, context -> log.info("initial SI"))
        //       .state(States.S1, context -> {
        //           try {
        //               Thread.sleep(1000);
        //           } catch (InterruptedException e) {
        //               throw new RuntimeException(e);
        //           }
        //           log.info("S1 entry");
        //       })
        //       .state(States.S2, context -> log.info("S2 entry"), context -> log.info("S2 exit"));

        states.withStates()
              .initial(States.SI)
              .junction(States.S1)
              .end(States.S3)
              .states(EnumSet.allOf(States.class));

    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        // transitions.withExternal()
        //            .source(States.SI).target(States.S1).event(Events.E1)
        //            .and()
        //            .withExternal()
        //            .source(States.S1)
        //            .target(States.S2)
        //            .event(Events.E2)
        //            .action(context -> {
        //                log.info("event action");
        //                throw new RuntimeException("action exception");
        //            }, context -> log.error("event action exception", context.getException()));

        transitions
                .withExternal()
                .source(States.SI)
                .target(States.S1)
                .event(Events.E1)
                .and()
                .withJunction()
                .source(States.S1)
                .first(States.S2, context -> {
                    log.info("first");
                    return true;
                })
                .then(States.S2, context -> {
                    log.info("last");
                    return false;
                })
                .last(States.S3, context -> log.info("then"));

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
