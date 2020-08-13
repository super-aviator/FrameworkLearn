package com.xqk.learn.springboot.data.rabbit.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author 熊乾坤
 * @date 2020-08-03 9:43
 */
@Getter
@Setter
@ToString
public class RabbitMessageSendVO {
    @NotEmpty(message = "change不能为空")
    private String exchange;

    @NotEmpty(message = "routing不能为空")
    private String routing;

    @NotEmpty(message = "message不能为空")
    private String message;
}
