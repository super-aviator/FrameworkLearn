package com.xqk.learn.framework.springboot.data.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * The type DatasourceUser detail.
 *
 * @author Aviator
 */
@Entity
@Data
@Table(name = "user_detail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREDIT")
    private Float credit;

    @Column(name = "ENROLLMENT_DATE")
    private Date enrollmentDate;

    //@Column(name = "USER_ID")
    @OneToOne(mappedBy = "userDetail")
    private User user;
}
