package com.xqk.lean.framework.springboot.tenantisolation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 熊乾坤
 * @since 2020-03-30 9:30
 */
@Data
@Entity
@Table(name = "product")
public class Product {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "desc")
    private String desc;

    @Column(name = "tenant")
    private String tenant;
}
