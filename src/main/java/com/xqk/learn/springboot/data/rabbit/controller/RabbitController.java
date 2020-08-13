package com.xqk.learn.springboot.data.rabbit.controller;

import com.xqk.learn.springboot.common.ResponseMessage;
import com.xqk.learn.springboot.data.rabbit.vo.RabbitMessageSendVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 熊乾坤
 * @date 2020-08-01 18:53
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    private final RabbitTemplate rabbitTemplate;

    public RabbitController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    public ResponseMessage getTemplate(@Validated @RequestBody RabbitMessageSendVO sendVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }

        rabbitTemplate.convertAndSend(sendVO.getExchange(), sendVO.getRouting(), sendVO.getMessage());
        return ResponseMessage.ok("发送成功");
    }
}
