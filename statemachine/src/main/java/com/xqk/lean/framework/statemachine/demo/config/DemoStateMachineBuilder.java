package com.xqk.lean.framework.statemachine.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import reactor.core.publisher.Mono;

import java.util.Arrays;

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
        builder.configureStates()
               .withStates()
               .initial("SI")
               .state("S1", context -> {
                   log.info("S1 entry");
                   var map = context.getExtendedState().getVariables();
                   map.put("count", Integer.parseInt(map.getOrDefault("count", 0).toString()) + 1);
               })
               .state("S2", Arrays.asList(context -> {
                   // try {
                       // Thread.sleep(5000);
                   // } catch (InterruptedException e) {
                   //     throw new RuntimeException(e);
                   // }
                   log.info("S2 entry1");
               }, context -> log.info("S2 entry2"), context -> log.info("S2 entry3")))
               .state("S3", context -> {
                   log.info("S3 entry");
                   log.info("S3 action header{}", context.getMessageHeaders().get("header"));
               });
        builder.configureTransitions()
               .withExternal()
               .source("SI")
               .target("S1")
               .event("E1")
               .and()
               .withExternal()
               .source("S2")
               .target("S1")
               .event("E2")
               .and()
               .withExternal()
               .source("S1")
               .target("S2")
               // .and()
               // .withExternal()
               // .source("S2")
               // .target("S3")
               .and()
               .withInternal()
               .source("S2")
               .action(context -> {
                   log.info("timer fired");
                   context.getStateMachine().sendEvent(Mono.just(MessageBuilder.withPayload("E2").setHeader("header", "value").build())).subscribe();
               })
               .timer(1000);
        return builder.build();
    }

    public static void main(String[] args) throws Exception {
        var stateMachine = build();
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("E1").setHeader("header", "value").build())).subscribe();
        log.info(stateMachine.getState().getId());
        log.info("{}", stateMachine.getExtendedState().get("count", Integer.class));
        Thread.sleep(11000);
    }
}
