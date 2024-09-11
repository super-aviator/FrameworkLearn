package com.xqk.lean.framework.springboot.junit.framework.mockmvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 通常@WebMvcTest只限于单个控制器（controller）使用，并结合@MockBean以提供需要的协作者（collaborators）的mock实现。
 * 注解@WebMvcTest也会自动配置MockMvc，Mock MVC为快速测试MVC控制器提供了一种强大的方式，并且不需要启动一个完整的HTTP服务器。
 *
 * 注解@WebMvcTest不能与@SpringBootTest一起使用思密达
 */
@Slf4j
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@WebMvcTest(TestController.class)
class TestControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    /** 用来替换已有的bean，Mock bean每次调用完测试方法后会自动重置。 */
    // @MockBean
    // private TestController testController;

    @Test
    public void helloWorldTestWithMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/helloWorld")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello World"));
    }
}