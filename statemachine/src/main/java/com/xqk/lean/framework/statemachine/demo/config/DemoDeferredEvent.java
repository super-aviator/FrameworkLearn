package com.xqk.lean.framework.statemachine.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import reactor.core.publisher.Mono;

/**
 * DemoDefferedEvent
 *
 * @author qiankun.xiong
 * @since 2024/9/8 21:26
 */
@Slf4j
public class DemoDeferredEvent {
    public static StateMachine<String, String> build() throws Exception {
        var builder = StateMachineBuilder.<String, String>builder();
        builder.configureConfiguration()
               .withConfiguration()
               .autoStartup(true)
               .listener(new StateMachineListenerAdapter<>() {
                   @Override
                   public void stateChanged(State<String, String> from, State<String, String> to) {
                       System.out.println("State change to " + to.getId());
                   }
               });
        builder.configureStates()
               .withStates()
               .initial("READY")
               .state("DEPLOYPREPARE1", "DEPLOY")
               .state("DEPLOYPREPARE2", "DEPLOY")
               .state("DEPLOYPREPARE3", "DEPLOY")
               .state("DEPLOYPREPARE4", "DEPLOY")
               .state("DEPLOYEXECUTE", "DEPLOY");

        builder.configureTransitions()
               .withExternal()
               .source("READY").target("DEPLOYPREPARE1")
               .event("DEPLOY")
               .and()
               .withExternal()
               .source("DEPLOYPREPARE1").target("DEPLOYPREPARE2")
               .and()
               .withExternal()
               .source("DEPLOYPREPARE2").target("DEPLOYPREPARE3")
               .and()
               .withExternal()
               .source("DEPLOYPREPARE3").target("DEPLOYPREPARE4")
               .and()
               .withExternal()
               .source("DEPLOYPREPARE4").target("DEPLOYEXECUTE")
               .and()
               .withExternal()
               .source("DEPLOYEXECUTE").target("READY");
        return builder.build();
    }

    public static void main(String[] args) throws Exception {
        var stateMachine = build();
        var flux=stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe();
        flux.dispose();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        new Thread(() -> stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("DEPLOY").build())).subscribe()).start();
        log.info(stateMachine.getState().getId());
        Thread.sleep(10000);
    }
}
