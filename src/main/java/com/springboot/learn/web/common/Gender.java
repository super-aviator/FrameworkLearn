package com.springboot.learn.web.common;

/**
 * @author Aviator
 */

public enum Gender {
    //男性
    MALE("男性"),
    //女性
    FEMALE("女性");

    private String value;
    Gender(String str){
        value=str;
    }
}
