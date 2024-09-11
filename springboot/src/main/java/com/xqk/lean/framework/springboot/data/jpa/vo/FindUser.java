package com.xqk.lean.framework.springboot.data.jpa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 熊乾坤
 * @since 2019-11-24 13:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindUser {
    private String name;
    private Long sumScore;
    private Date enrollmentDate;
    private String addressConcat;
}
