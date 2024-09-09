package com.xqk.learn.framework.statemachine.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import reactor.core.publisher.Mono;

/**
 * DemoStateMachineBuilder
 *
 * @author qiankun.xiong
 * @since 2024/9/8 18:42
 */
@Slf4j
public class DemoStateMachineBuilder {
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
        builder
                .configureStates()
                .withStates()
                .initial("SI")
                .state("S1", context -> log.info("S1 entry"))
                .state("S2", context -> log.info("S2 entry"))
                .state("S3", context -> log.info("S3 entry"));

        builder.configureTransitions()
               .withExternal()
               .source("SI")
               .target("S1")
               .event("E1")
               .and()
               .withExternal()
               .source("S1")
               .target("S2")
               .and()
               .withExternal()
               .source("S2")
               .target("S3");
        // .source("SI")
        // .target("S1")
        // .event("E1");
        return builder.build();
    }

    public static void main(String[] args) throws Exception {
        var stateMachine = build();
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("E1").build())).subscribe();
        log.info(stateMachine.getState().getId());
        Thread.sleep(10000);
    }
}
