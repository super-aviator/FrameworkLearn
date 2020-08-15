package com.xqk.learn.springboot.data.elasticsearch.entity;

import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author 熊乾坤
 * @date 2020-05-18 20:44
 */
@Data
@Profile("elasticsearch")
@Document(indexName = "mall", type = "goods")
public class Item {
    /** id */
    @Id
    private Long id;

    /** 姓 */
    private String firstName;

    /** 名 */
    private String lastName;

    /** 年龄 */
    private Integer age;

    /** 关于 */
    private String about;
}
