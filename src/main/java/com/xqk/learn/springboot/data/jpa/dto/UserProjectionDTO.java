package com.xqk.learn.springboot.data.jpa.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;


/**
 * The type DatasourceUser projection dto.
 *
 * @author Aviator
 */
@Data
@ToString
public class UserProjectionDTO {
    /**
     * 使用Projection时，字段必须为final修饰
     */
    private String name, email;
}