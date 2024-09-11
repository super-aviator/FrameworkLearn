package com.xqk.lean.framework.springboot.data.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;


/**
 * The type DatasourceUser friend.
 *
 * @author Aviator
 */
@Entity
@Data
@Table(name = "user_friend")
public class UserFriend {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    private Long userId;

    @Column(name = "FRIEND_ID")
    private Long friendId;
}
