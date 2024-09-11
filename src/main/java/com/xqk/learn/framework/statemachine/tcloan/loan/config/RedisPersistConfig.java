package com.xqk.learn.framework.statemachine.tcloan.loan.config;

import com.xqk.learn.framework.statemachine.demo.event.Events;
import com.xqk.learn.framework.statemachine.demo.state.States;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanEvents;
import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanStates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.data.redis.RedisPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.data.redis.RedisStateMachineRepository;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

/**
 * RedisPersistConfig
 *
 * @author qiankun.xiong
 * @since 2024/9/10 22:36
 */
@Configuration
public class RedisPersistConfig {
    @Bean
    public StateMachineRuntimePersister<LoanStates, LoanEvents, String> stateMachineRuntimePersister(RedisStateMachineRepository jpaStateMachineRepository) {
        return new RedisPersistingStateMachineInterceptor<>(jpaStateMachineRepository);
    }

    @Bean
    public StateMachinePersist<LoanStates, LoanEvents, String> stateMachinePersist(RedisConnectionFactory connectionFactory) {
        RedisStateMachineContextRepository<LoanStates, LoanEvents> repository = new RedisStateMachineContextRepository<>(connectionFactory);
        return new RepositoryStateMachinePersist<>(repository);
    }

    @Bean
    public RedisStateMachinePersister<LoanStates, LoanEvents> redisStateMachinePersister(StateMachinePersist<LoanStates, LoanEvents, String> stateMachinePersist) {
        return new RedisStateMachinePersister<>(stateMachinePersist);
    }
}
