package com.xqk.learn.framework.transaction.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 熊乾坤
 * @since 2021-05-06 19:18
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "transaction_propagation_outside")
public class OutsideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public OutsideEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
