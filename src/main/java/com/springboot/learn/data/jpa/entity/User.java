package com.springboot.learn.data.jpa.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.learn.data.jpa.common.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * The type User.
 *
 * @author Aviator
 */
@Data
@Entity
@Table(name = "USER")
@NamedEntityGraph(
        name = "UserEntity",
        attributeNodes = {
                @NamedAttributeNode("userDetail"),
                @NamedAttributeNode("userFriends"),
                @NamedAttributeNode("teachers"),
        }
)
public class User {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 使用version字段实现乐观锁，乐观锁的测试代码在OptimisticLockTest类中
     */
    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "SCORE")
    private Integer score;

    /**
     * 一对一
     */
    @JoinColumn(name = "id",referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.ALL})
    private UserDetail userDetail;

    /**
     * 一对多
     */
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @OrderBy("friend_id ASC")
    private Set<UserFriend> userFriends;

    /**
     * 多对多
     * joinColumns中的name和inverseJoinColumns中的name可以填关系表TEACHER_USER_RELATION的实体的字段名
     * 如果关系表TEACHER_USER_RELATION的实体中的字段使用了@Column(name)，那么需要填写注解中指定的名字，即表字段名。
     * referencedColumnName填写的是本实体类中的关联的字段。
     */
    @JsonIgnoreProperties(value = { "users" })
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "TEACHER_USER_RELATION",
            joinColumns = @JoinColumn(name = "UserId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "TeacherId",referencedColumnName = "id")
    )
    private Set<Teacher> teachers;
}
