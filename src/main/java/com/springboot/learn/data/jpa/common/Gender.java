package com.springboot.learn.data.jpa.common;

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
    MALE("男性"),
    /**
     * Female gender.
     */
//女性
    FEMALE("女性");

    private String value;
    Gender(String str){
        value=str;
    }
}
