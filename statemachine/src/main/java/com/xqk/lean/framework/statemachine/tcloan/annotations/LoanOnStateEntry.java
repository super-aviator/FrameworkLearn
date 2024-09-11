package com.xqk.lean.framework.statemachine.tcloan.annotations;

import com.xqk.learn.framework.statemachine.tcloan.loan.enums.LoanStates;
import org.springframework.statemachine.annotation.OnStateChanged;

import java.lang.annotation.*;

/**
 * LoanOnStateEntry
 *
 * @author qiankun.xiong
 * @since 2024/9/10 21:08
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@OnStateChanged
public @interface LoanOnStateEntry {
    LoanStates[] source() default {};

    LoanStates[] target() default {};
}
