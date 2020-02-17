package com.xqk.learn.springboot.data.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 熊乾坤
 * @date 2020-01-12 12:56
 */
@Data
@Entity
@Table(name = "lazy_fetch_test")
public class LazyFetchTestEntity {
    @Id
    @GeneratedValue
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
}
