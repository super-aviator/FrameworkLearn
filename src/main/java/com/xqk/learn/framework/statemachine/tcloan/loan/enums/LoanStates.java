package com.xqk.learn.framework.statemachine.tcloan.loan.enums;

/**
 *
 *
 * @author qiankun.xiong
 * @version 1.0.0
 * @since 2024/9/10 14:14
 */
public enum LoanStates {
    /** 初始化 */
    I000,
    /** 初始校验 */
    I005, I010, I015,
    /** 申请校验 */
    A005, B005, B010,
    /** 借款前置申请 */
    LP001, LP005,
    /** 前置申请成功 */
    LP010,
    /** 借款申请 */
    LA01, LA05,
    /** 借款申请成功 */
    LA10, S001, S005, S010,
    /** 申请失败 */
    S091,
    /** 前置申请处理中 */
    W,

}
