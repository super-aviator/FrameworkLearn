package com.xqk.lean.framework.springboot.data.jpa;

import com.xqk.learn.framework.springboot.data.jpa.entity.CareerEntity;
import com.xqk.learn.framework.springboot.data.jpa.repository.CareerRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
public class MysqlJsonDataTypeTest {
    @Autowired
    private CareerRepository careerRepository;

    @Test
    public void test() {
        CareerEntity entity = new CareerEntity();
        entity.setName("熊乾坤");
        //使用Set也可以
        entity.setHobbies("\"唱\", \"跳\", \"rap\", \"篮球\"");
        entity.setWorks("");
        entity = careerRepository.save(entity);
        log.info(entity.toString());
        Optional.of(careerRepository.findById(entity.getId()))
                .map(e -> e.isPresent() ? e.toString() : "null")
                .ifPresent(log::info);
    }
}
