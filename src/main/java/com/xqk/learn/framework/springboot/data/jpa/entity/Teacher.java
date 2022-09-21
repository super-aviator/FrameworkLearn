package com.xqk.learn.framework.springboot.data.jpa.entity;

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
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    //@JsonIgnoreProperties(value = {"users"})
    @ManyToMany(mappedBy = "teachers")
    private List<User> users;
}
