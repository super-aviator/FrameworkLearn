package com.xqk.learn.framework.junit.framework.mockmvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Framework 5.0提供了一个新的WebTestClient。它服务于WebFlux集成测试与WebFlux与MVC端到端测试。
 * 不像TestRestTemplate，它为断言提供了流畅的API。
 * 需要依赖spring-boot-starter-webflux
 */
@Slf4j
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerWebTestClientTest {
    /** 用于获取运行时的端口（运行模式为WebEnvironment.MOCK时无法使用） */
    @LocalServerPort
    private Integer port;


    /** WebEnvironment.RANDOM_PORT时可用 */
    @Autowired
    // private WebTestClient webTestClient;

    @Test
    public void helloWorldTestWithWebTestClient() {
        // log.info("runtime port：" + port);

        //使用webTestClient
        // webTestClient.get()
        //              .uri("/test/helloWorld")
        //              .accept(MediaType.APPLICATION_JSON)
        //              .exchange()
        //              .expectStatus()
        //              .isOk()
        //              .expectBody(String.class)
        //              .isEqualTo("Hello World");
    }
}