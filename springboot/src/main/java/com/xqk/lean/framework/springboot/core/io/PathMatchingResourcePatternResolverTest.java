package com.xqk.lean.framework.springboot.core.io;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * ant风格的路径:
 * 1. ?匹配一个字符
 * 2. *匹配任意个字符
 * 3. **匹配任意多个路径
 * <p>
 * ResourceLoader<-ResourcePatternResolver,PathMatchingResourcePatternResolver是ResourcePatternResolver
 * 类的实现类,ResourceLoader中的getResource不支持ant风格的路径,支持资源前缀;ResourcePatternResolver中新增了
 * 一个getResources方法,支持资源前缀和ant风格的路径.
 * <p>
 * 注意,当通过getResources方法获取到的Resource对象时,如果文件是在jar包中,不能调用getFile方法,只能调用getInputStream方法获取文件内容
 * <p>
 * 日期 2019/10/9 10:33
 *
 * @author 熊乾坤
 */
public class PathMatchingResourcePatternResolverTest {
    public static void main(String[] args) throws IOException {
        ResourcePatternResolver rl = new PathMatchingResourcePatternResolver();

        //String path = "classpath*:com/xqk/learn/springboot/**/*.class";
        String path = "classpath*:org/springframework/**/*.class";
        Resource[] resources = rl.getResources(path);
        for (Resource resource : resources) {
            System.out.println("**->" + resource.getDescription());
            //System.out.println(resource.getInputStream());
            //System.out.println(resource.getFile());
        }
    }
}
