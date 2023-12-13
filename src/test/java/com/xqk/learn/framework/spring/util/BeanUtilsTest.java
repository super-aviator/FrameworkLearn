package com.xqk.learn.framework.spring.util;

import cn.hutool.core.bean.BeanUtil;
import com.xqk.learn.framework.spring.util.pojo.Cla1;
import com.xqk.learn.framework.spring.util.pojo.Cla1Derived;
import com.xqk.learn.framework.spring.util.pojo.Cla2;
import com.xqk.learn.framework.spring.util.pojo.ContainCla1AndCla2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * 1. BeanUtils工具类拷贝的时候，不会覆盖掉类原来已有的属性
 * 2. BeanUtils工具类可以拷贝父类的属性
 *
 * @author 熊乾坤
 * @since 2020-03-08 11:25
 */
@Slf4j
public class BeanUtilsTest {

    /**
     * 属性为null的，在拷贝的时候不会忽略。
     */
    @Test
    public void testCopyProperty() {
        Cla1 cla1 = new Cla1();
        cla1.setName("xqk");
        Cla2 cla2 = new Cla2();
        cla2.setAge(10);
        BeanUtils.copyProperties(cla2, cla1, "name");
        //BeanUtil.copyProperties(cla2, cla1);
        log.info("******{}", cla1);
    }


    @Test
    public void testCopySuperClassProperty() {
        Cla1Derived derived = new Cla1Derived();
        derived.setId(1L);
        derived.setAge(20);
        derived.setName("xqk");
        ContainCla1AndCla2 contain = new ContainCla1AndCla2();
        BeanUtil.copyProperties(derived, contain);
        System.out.println(contain);
    }
}
