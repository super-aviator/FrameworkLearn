package com.springboot.learn.data.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * The type User detail.
 *
 * @author Aviator
 */
@Entity
@Data
@Table(name = "USER_DETAIL")
public class UserDetail {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREDIT")
    private Float credit;

    @Column(name = "ENROLLMENT_DATE")
    private Date enrollmentDate;

    @Column(name = "USER_ID")
    private Long userId;
}