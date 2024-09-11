package com.xqk.lean.framework.springboot.junit.framework.mockmvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@AutoConfigureMockMvc
// @AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerRestTemplateTest {
    /** 用于获取运行时的端口（运行模式为WebEnvironment.MOCK时无法使用） */
    @LocalServerPort
    private Integer port;

    /** WebEnvironment.RANDOM_PORT时可用，也可以直接new一个进行使用 */
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void helloWorldTestWithTestRestTemplate() {
        var response = restTemplate.exchange("/test/helloWorld", HttpMethod.GET, null, String.class);
        // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Hello World");
    }

    /**
     * 定制TestRestTemplate
     */
    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(1000))
                                            .setReadTimeout(Duration.ofMillis(1000));
        }
    }
}