package com.xqk.learn.framework.mvc.mockmvc;

import com.xqk.learn.framework.springboot.data.jpa.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * The type DatasourceUser controller test.
 */
@SpringBootTest
@Slf4j
public class DatasourceDatasourceUserControllerTest {
    private static MockMvc mockMVC;

    /**
     * Before.
     */
    @BeforeAll
    public static void before() {
        mockMVC = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    /**
     * Test.
     *
     * @throws Exception the exception
     */
    @Test
    public void test() throws Exception {
        log.info("****" + mockMVC.perform(MockMvcRequestBuilders.get("user/all")
                .accept(MediaType.ALL))
                .andReturn().getResponse()
                .getContentAsString());
    }
}