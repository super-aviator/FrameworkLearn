package com.xqk.learn.springboot.data.redis;

import com.xqk.learn.springboot.common.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * RedisController
 *
 * @author xiongqiankun
 * @since 2022/7/27 13:30
 */
@Profile("redis")
@RequestMapping("/redis")
@RestController
@RequiredArgsConstructor
public class RedisController {
    private final StringRedisTemplate stringRedisTemplate;

    @PutMapping("/list/add")
    public ResponseMessage<Long> addMessageToList(@RequestParam("queueName") String queueName, @RequestParam("message") String message) {
        return ResponseMessage.ok(stringRedisTemplate.opsForList().leftPush(queueName, message));
    }

    @GetMapping("/list/get")
    public ResponseMessage<String> addMessageToList(@RequestParam("queueName") String queueName) {
        return ResponseMessage.ok(stringRedisTemplate.opsForList().rightPop(queueName));
    }

    @PutMapping("/streams/add")
    public ResponseMessage<Long> addMessageToStreams(@RequestParam("streamsName") String queueName, @RequestParam("message") String message) {
        return ResponseMessage.ok(stringRedisTemplate.opsForList().leftPush(queueName, message));
    }

    @GetMapping("/streams/get")
    public ResponseMessage<String> addMessageToStreams(@RequestParam("streamsName") String queueName) {
        return ResponseMessage.ok(stringRedisTemplate.opsForList().rightPop(queueName));
    }
}
