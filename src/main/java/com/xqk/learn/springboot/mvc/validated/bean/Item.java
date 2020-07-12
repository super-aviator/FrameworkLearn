package com.xqk.learn.springboot.mvc.validated.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 注解@Length的字段，可以为空。
 *
 * @author 熊乾坤
 * @date 2020-05-24 18:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Item extends ItemBase {
    @NotNull(message = "name不能为空")
    private String name;

    @Max(value = 100, message = "非法的年龄")
    @Min(value = 0, message = "非法的年龄")
    private Integer age;

    @Length(max = 10, message = "非法的身份证号")
    private String idNumber;

    @Digits(integer = 90, fraction = 100)
    private Double longitude;
}
