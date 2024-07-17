package com.xqk.learn.framework.springboot.data.jpa.datasource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.context.annotation.Profile;


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
