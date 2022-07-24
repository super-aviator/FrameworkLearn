package com.xqk.learn.springboot.mybatis.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.util.*;

/**
 * Mapper名字生成器
 */

public class MapperNameGenerator implements BeanNameGenerator {
    Map<String,Integer> nameMap = new HashMap<>();

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        //获取类的名字，如CountryMapper
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());
        //将类名转换为规范的变量名，如
        String beanName = Introspector.decapitalize(shortClassName);
        //判断名字是否已经存在，如果存在，则在名字后面增加序号
        if (nameMap.containsKey(beanName)) {
            int index = nameMap.get(beanName) + 1;
            nameMap.put(beanName, index);
            //增加序号
            beanName += index;
        } else {
            nameMap.put(beanName, 1);
        } return beanName;
    }

    public static void main(String[] args) {
        Arrays.toString(".1".split("\\."));
        List<String> List=new ArrayList<>();
        List.add("a");
        System.out.println(Arrays.toString(List.toArray(new String[0])));
    }
}