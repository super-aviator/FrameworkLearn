package com.xqk.learn.framework.cloud.openfeign.builder;

import cn.hutool.core.io.IoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import feign.Feign;
import feign.Param;
import feign.RequestLine;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("POST /repos/{owner}/{repo}/issues")
    void createIssue(Issue issue, @Param("owner") String owner, @Param("repo") String repo);

    @Getter
    @Setter
    class Contributor {
        String login;
        int contributions;
    }

    @Getter
    @Setter
    class Issue {
        String title;
        String body;
        List<String> assignees;
        int milestone;
        List<String> labels;
    }

    class MyApp {
        public static void main(String... args) {
            ObjectMapper mapper = JsonMapper.builder().build();
            GitHub github = Feign.builder()
                    //读取响应体中的String，并转换为JSOn格式
                    // .decoder((response, type) -> JSONObject.parseObject(IoUtil.read(response.body().asReader()), type))
                    .target(GitHub.class, "https://api.github.com");

            // Fetch and print a list of the contributors to this library.
            List<Contributor> contributors = github.contributors("super-aviator", "SpringBootLearn");
            for (Contributor contributor : contributors) {
                System.out.println(contributor.login + " (" + contributor.contributions + ")");
            }
        }
    }
}