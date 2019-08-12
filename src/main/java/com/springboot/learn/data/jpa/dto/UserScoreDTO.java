package com.springboot.learn.data.jpa.dto;

import lombok.Data;

/**
 * @author Aviator
 */
@Data
public class UserScoreDTO {
    private String name, email;
    private Double scoreAvg;

    public UserScoreDTO(String name, String email, Double scoreAvg) {
        this.name = name;
        this.email = email;
        this.scoreAvg = scoreAvg;
    }
}
