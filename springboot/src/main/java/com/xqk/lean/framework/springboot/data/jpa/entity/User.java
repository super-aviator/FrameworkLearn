package com.xqk.lean.framework.springboot.data.jpa.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xqk.lean.framework.springboot.data.jpa.common.Gender;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * The type DatasourceUser.
 *
 * @author Aviator
 */
@Data
@Entity
@Table(name = "user")
@NamedEntityGraph(name = "UserEntity", attributeNodes = {
        //@NamedAttributeNode("userDetail"),
        @NamedAttributeNode("userFriends"), @NamedAttributeNode("teachers"),})
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 使用version字段实现乐观锁，乐观锁的测试代码在OptimisticLockTest类中
     */
    @Version
    @Column(name = "VERSION")
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

    @Column(name = "BIRTHDAY")
    private Date birthday;

    //@Column(name = "USER_DETAIL_ID")
    //private Long userDetailId;

    /**
     * 一对一
     */
    @JsonIgnoreProperties(value = {"user"})
    @JoinColumn(name = "USER_DETAIL_ID")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserDetail userDetail;

    /**
     * 一对多
     */
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @OrderBy("friend_id ASC")
    private Set<UserFriend> userFriends;

    /**
     * 多对多
     * joinColumns中的name和inverseJoinColumns中的name可以填关系表TEACHER_USER_RELATION的实体的字段名
     * 如果关系表TEACHER_USER_RELATION的实体中的字段使用了@Column(name)，那么需要填写注解中指定的名字，即表字段名。
     * referencedColumnName填写的是本实体类中的关联的字段。
     */
    @JsonIgnoreProperties(value = {"users"})
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_user_relation",
               joinColumns = @JoinColumn(name = "UserId", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "TeacherId", referencedColumnName = "id"))
    private Set<Teacher> teachers;

    @Transient
    private UserDetail userDetailDup;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id) && Objects.equals(version, user.version) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(address,
                user.address) && gender == user.gender && Objects.equals(score, user.score) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, address, gender, score, birthday);
    }
}
