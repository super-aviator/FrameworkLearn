package com.springboot.learn;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Aviator
 */
@SpringBootApplication
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication();
        springApplication.setSources(new HashSet<>(Arrays.asList("com.springboot.learn")));
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

}
