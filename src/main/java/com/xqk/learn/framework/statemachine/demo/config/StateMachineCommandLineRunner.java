package com.xqk.learn.framework.statemachine.demo.config;

import com.xqk.learn.framework.statemachine.demo.event.Events;
import com.xqk.learn.framework.statemachine.demo.state.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * CommandLineRunner
 *
 * @author qiankun.xiong
 * @since 2024/7/7 13:36
 */
// @Component
public class StateMachineCommandLineRunner implements CommandLineRunner {
    // @Autowired
    // private StateMachine<States, Events> stateMachine;
    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;

    @Override
    public void run(String... args) throws Exception {
        // stateMachine.sendEvent(Events.E1);
        // Thread.sleep(1000);
        // stateMachine.sendEvent(Events.E2);

        // stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E1).build())).subscribe();
        // // Thread.sleep(1000);
        // stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E2).build())).subscribe();

        String machineId = "xqk-state-machine";
        var stateMachine = stateMachineFactory.getStateMachine(machineId);
        stateMachine.startReactively().subscribe();
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E1).build())).subscribe();
        Thread.sleep(1000);
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E2).build())).subscribe();
    }
}
