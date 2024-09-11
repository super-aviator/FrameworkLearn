package com.xqk.lean.framework.springboot.data.jpa.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * 工作详情pojo类
 *
 * @author 熊乾坤
 * @since 2020-03-01 13:02
 */
@Data
@Builder
public class WorkPojo {
    /** 工作名称 */
    private String workName;
    /** 薪水 */
    private Double salary;
    /** 老板信息 */
    private BossPojo bossPojo;
}
