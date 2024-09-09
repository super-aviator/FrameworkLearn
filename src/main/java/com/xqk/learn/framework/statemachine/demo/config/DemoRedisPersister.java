package com.xqk.learn.framework.statemachine.demo.config;

import com.xqk.learn.framework.statemachine.demo.event.Events;
import com.xqk.learn.framework.statemachine.demo.state.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.action.StateDoActionPolicy;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.state.State;
import reactor.core.publisher.Mono;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * DemoRedisPersister
 *
 * @author qiankun.xiong
 * @since 2024/9/8 23:14
 */
@Slf4j
@Configuration
@EnableStateMachine(name = "redis-state-machine")
public class DemoRedisPersister extends EnumStateMachineConfigurerAdapter<States, Events> implements CommandLineRunner {
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
              .autoStartup(true)
              .stateDoActionPolicy(StateDoActionPolicy.TIMEOUT_CANCEL)
              .stateDoActionPolicyTimeout(10, TimeUnit.SECONDS)
              .listener(listener())
        ;
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {

        states.withStates()
              .initial(States.SI)
              .states(EnumSet.allOf(States.class));

    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {


        transitions
                .withExternal()
                .source(States.SI)
                .target(States.S1)
                .event(Events.E1);

    }

    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                log.info("state change to " + to.getId());
            }
        };
    }

    @Bean
    public StateMachinePersist<States, Events, String> stateMachinePersist(RedisConnectionFactory connectionFactory) {
        RedisStateMachineContextRepository<States, Events> repository = new RedisStateMachineContextRepository<>(connectionFactory);
        return new RepositoryStateMachinePersist<>(repository);
    }

    @Bean
    public RedisStateMachinePersister<States, Events> redisStateMachinePersister(StateMachinePersist<States, Events, String> stateMachinePersist) {
        return new RedisStateMachinePersister<>(stateMachinePersist);
    }

    @Autowired
    private RedisStateMachinePersister<States, Events> persister;
    @Autowired
    @Qualifier("redis-state-machine")
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E1).build())).blockLast();
        log.info(stateMachine.getState().getId().name());
        persister.persist(stateMachine, "abc");
        persister.restore(stateMachine, "abc");
        log.info(stateMachine.getState().getId().name());
    }
}
