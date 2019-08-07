package com.springboot.learn.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author 熊乾坤
 */
@RestController
@RequestMapping("/multipart")
@Slf4j
public class MultipartController {
    /**
     * 使用Multipart进行文件下载,post请求需要在SpringSecurity中关闭CSRF保护或者在请求中携带CSRF参数
     *
     * @param file     文件
     * @param response 响应
     */
    @PostMapping("/fileUpload")
    public void uploadFileEcho(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        //定义文件下载的头部，表示这是一个流
        response.setContentType("application/octet-stream");
        //定义文件下载后的名字
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getOriginalFilename());
        log.info("后台获取到文件：{}", file.getOriginalFilename());
        byte[] buffer = new byte[1024];

        try (InputStream in = file.getInputStream(); OutputStream out = response.getOutputStream()) {
            while (in.read(buffer) != -1) {
                out.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
