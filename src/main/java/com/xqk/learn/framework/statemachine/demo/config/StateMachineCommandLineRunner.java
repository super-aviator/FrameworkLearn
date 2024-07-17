package com.xqk.learn.framework.statemachine.demo.config;

import com.xqk.learn.framework.statemachine.demo.event.Events;
import com.xqk.learn.framework.statemachine.demo.state.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * CommandLineRunner
 *
 * @author qiankun.xiong
 * @since 2024/7/7 13:36
 */
@Component
public class StateMachineCommandLineRunner implements CommandLineRunner {
    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E1).build()));
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E1).build()));
    }
}
