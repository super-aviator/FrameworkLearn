package com.xqk.learn.framework.statemachine.tcloan.loan.enums;

/**
 * Events
 *
 * @author qiankun.xiong
 * @since 2024/9/10 14:17
 */
public enum LoanEvents {
    /** 提交 */
    APPLY,
    /** 借款前置结果 */
    LOAN_PRE_APPLY_RESULT,
    /** 补充资料 */
    SUPPLEMENT,
    /** 借款结果 */
    LOAN_APPLY_RESULT,
}
