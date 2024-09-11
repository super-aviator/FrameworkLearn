package com.xqk.lean.framework.springboot.core.io;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.*;

/**
 * 通过ClassPathResource类,可以直接读取ClassPath路径下的文件
 * 通过FileSystemResource,可以直接读取系统文件路径下的文件
 * 通过ServletContextResource类,可以直接读取容器类路径下的文件
 *
 * @author 熊乾坤
 * @since 2019/10/8 8:42
 */
public class AccessClassPathResource {
    public static void main(String[] args) throws IOException {
        String classPath = "application-";
        ClassPathResource classPathResource = new ClassPathResource(classPath);
        InputStreamReader reader = new InputStreamReader(classPathResource.getInputStream());
        int i;
        while ((i = reader.read()) != -1) {
            System.out.print((char) i);
        }
        System.out.println();

        //使用createRelative方法在原来的路径上创建新的文件
        String path = "C:\\Users\\Public\\Downloads\\";
        FileSystemResource fileSystemResource = new FileSystemResource(path);
        Resource resource = fileSystemResource.createRelative("Hello.txt");
        File file = resource.getFile();
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.err.println("文件创建失败");
                return;
            }
        }

        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write("Hello World".getBytes());
        }
    }
}
