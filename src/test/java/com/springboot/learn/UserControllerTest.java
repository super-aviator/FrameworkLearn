package com.springboot.learn;

import com.springboot.learn.web.controller.UserController;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {
    private MockMvc mockMVC;

    @Before
    public void before() {
        mockMVC = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void test() throws Exception {
        log.info("****" + mockMVC.perform(MockMvcRequestBuilders.get("user/all")
                .accept(MediaType.ALL))
                .andReturn().getResponse()
                .getContentAsString());
    }
}
