package com.xqk.learn.springboot.core.resource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 使用EncodeResource对资源文件进行编码
 *
 * @author 熊乾坤
 * @date 2019/10/8 10:04
 */
public class EncodeResourceTest {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Public\\Downloads\\Hello.txt";
        FileSystemResource fileSystemResource = new FileSystemResource(path);
        System.out.println(FileCopyUtils.copyToString(new InputStreamReader(fileSystemResource.getInputStream())));
        //编码转换
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
        System.out.println(FileCopyUtils.copyToString(encodedResource.getReader()));
    }
}