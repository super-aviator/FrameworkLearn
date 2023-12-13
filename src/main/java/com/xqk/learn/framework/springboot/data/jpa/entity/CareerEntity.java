package com.xqk.learn.framework.springboot.data.jpa.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import com.xqk.learn.framework.springboot.data.jpa.pojo.WorkPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * 测试使用Mysql的JSON数据类型
 * 职业生涯实体类
 *
 * @author 熊乾坤
 * @since 2020-03-01 12:58
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "career")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class CareerEntity {
    /** 自增主键 */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    /** 个人爱好 */
    @Type(type = "json")
    @Column(name = "hobbies", columnDefinition = "json")
    private Set<String> hobbies;

    /** 工作经历 */
    @Type(type = "json")
    @Column(name = "works", columnDefinition = "json")
    private List<WorkPojo> works;
}
