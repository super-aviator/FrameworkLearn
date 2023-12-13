package com.xqk.learn.framework.springboot.data.jpa.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * 老板详细信息pojo类
 *
 * @author 熊乾坤
 * @since 2020-03-01 13:02
 */
@Data
@Builder
public class BossPojo {
    /** 名称 */
    private String name;
    /** 身价 */
    private Long worth;
}
