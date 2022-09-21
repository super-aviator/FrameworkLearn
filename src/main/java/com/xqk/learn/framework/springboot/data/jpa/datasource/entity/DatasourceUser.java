package com.xqk.learn.framework.springboot.data.jpa.datasource.entity;

import lombok.Data;
import org.springframework.context.annotation.Profile;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 熊乾坤
 * @since 2020-01-06 13:54
 */
@Data
@Entity
@Profile("datasource")
@Table(name = "APP_USER")
public class DatasourceUser {
    @Id
    private Long id;
    private String name;
    private Long age;
    private String address;
}
