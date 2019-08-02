package com.springboot.learn.data.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * The type Teacher.
 *
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
