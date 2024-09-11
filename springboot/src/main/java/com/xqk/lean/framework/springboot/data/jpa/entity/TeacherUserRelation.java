package com.xqk.lean.framework.springboot.data.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;


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
