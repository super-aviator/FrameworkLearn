package com.xqk.learn.framework.springboot.data.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * The type Teacher user relation.
 *
 * @author Aviator
 */
@Entity
@Data
@Table(name = "teacher_user_relation")
public class TeacherUserRelation {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    //@Column(name = "USER_ID")
    private Long userId;

    //@Column(name = "FRIEND_ID")
    private Long friendId;
}
