package com.springboot.learn.spring.data.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Aviator
 */
@Entity
@Data
@Table(name = "TEACHER", schema = "jpa", catalog = "jpa")
public class Teacher {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "teachers")
    private List<User> users;
}
