package com.xqk.learn.framework.data.jpa;

import com.xqk.learn.framework.springboot.data.jpa.entity.CareerEntity;
import com.xqk.learn.framework.springboot.data.jpa.pojo.BossPojo;
import com.xqk.learn.framework.springboot.data.jpa.pojo.WorkPojo;
import com.xqk.learn.framework.springboot.data.jpa.repository.CareerRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

/**
 * 测试JPA使用Mysql中的JSON数据格式
 * <p>
 * 问：mysql中对应的["1","2"]这种json格式，是否可以使用Set和List数据结构去接收和存储？
 * 答：可以，区别是使用Set是无顺序的。
 * <p>
 * 使用Hibernate-Type包，如果数据类型是List、Set这种集合形式，需要确保存入List中的数据都是非null的，否则在持久化的时候会抛出异常
 *
 * @author 熊乾坤
 * @since 2020-03-01 13:20
 */
@Slf4j
@SpringBootTest
@NoArgsConstructor
@RunWith(SpringRunner.class)
public class MysqlJsonDataTypeTest {
    @Autowired
    private CareerRepository careerRepository;

    @Test
    public void test() {
        CareerEntity entity = new CareerEntity();
        entity.setName("熊乾坤");
        //使用Set也可以
        entity.setHobbies(new HashSet<>(Arrays.asList("唱", "跳", "rap", "篮球")));
        entity.setWorks(Arrays.asList(
                WorkPojo.builder()
                        .workName("Java开发工程师")
                        .salary(8000.0)
                        .bossPojo(null)
                        .build(),
                WorkPojo.builder()
                        .workName("JavaK开发工程师")
                        //.salary(10000.0)  //设置为空，看一下是否会忽略
                        .bossPojo(BossPojo.builder()
                                .name(null)
                                .worth(100000000008L)
                                .build())
                        .build()));
        entity = careerRepository.save(entity);
        log.info(entity.toString());
        Optional.ofNullable(careerRepository.findById(entity.getId()))
                .map(e -> e.isPresent() ? e.toString() : "null")
                .ifPresent(log::info);
    }
}
