package com.xqk.learn.framework.springboot.data.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 测试实体类
 *
 * @author 熊乾坤
 * @since 2020-01-12 12:56
 */
@Data
@Entity
@Table(name = "test_column")
@DynamicInsert
public class TestColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    @Basic(fetch = FetchType.LAZY)
    private String address;

    @Column(name = "desc")
    @Basic(fetch = FetchType.LAZY)
    private String desc;

    @Column(name = "decimal_column")
    private BigDecimal bigDecimal;
}
