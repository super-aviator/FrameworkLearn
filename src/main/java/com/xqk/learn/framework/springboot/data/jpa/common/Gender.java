package com.xqk.learn.framework.springboot.data.jpa.common;

/**
 * The enum Gender.
 *
 * @author Aviator
 */
public enum Gender {
    /**
     * Male gender.
     */
//男性
    MALE("男性", 1),
    /**
     * Female gender.
     */
//女性
    FEMALE("女性", 2);

    private String str;
    private int value;

    Gender(String str, int value) {
        this.str = str;
        this.value = value;
    }
}
