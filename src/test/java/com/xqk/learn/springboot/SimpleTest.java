package com.xqk.learn.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 开发过程中的简单的测试类
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@Slf4j
public class SimpleTest {

    @Autowired
    DataSource dataSource;

    /**
     * null也是对象，可以对null进行强转，结果为null,但是不能将其赋值给基本类型的变量;
     */
    @Test
    public void testCastNull() {
        //log.info(dataSource.toString());
        //log.info((String) null);
        //
        //Integer i = (Integer) null;
        //System.out.println(i);
        //
        //int i2 = (Integer) null;
        //System.out.println(i2);

        //System.out.println(UUID.randomUUID().toString());
        //List<String> list1=Arrays.asList(null,"12.0",null,"10.5",null,"30.5","20.4");
        List<String> list1 = Arrays.asList("1");
        Optional optional = list1.stream()
                .reduce((val1, val2) -> {
                    if (val1 == null) {
                        return val2;
                    }

                    if (val2 == null) {
                        return val1;
                    }

                    return Double.valueOf(val1).compareTo(Double.valueOf(val2)) > 0 ? val1 : val2;
                });
        optional.ifPresent(System.out::println);
    }
    /**
     * Test split.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void testSplit() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        System.out.println((System.currentTimeMillis() - start) / 1000);
    }

    @Test
    public void test() {
        log.info(String.valueOf(Double.parseDouble("200")));
    }

    @Test
    public void testBoolean() {
//        Boolean isNull = null;
//        if (isNull) {
//            log.info(isNull + "");
//        }
//        System.out.println(1 + 1.1);
    }
}
