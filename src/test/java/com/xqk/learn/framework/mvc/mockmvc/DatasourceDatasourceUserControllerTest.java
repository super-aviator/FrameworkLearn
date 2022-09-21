package com.xqk.learn.framework.mvc.mockmvc;

import com.xqk.learn.framework.springboot.data.jpa.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * The type DatasourceUser controller test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class DatasourceDatasourceUserControllerTest {
    private MockMvc mockMVC;

    /**
     * Before.
     */
    @Before
    public void before() {
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