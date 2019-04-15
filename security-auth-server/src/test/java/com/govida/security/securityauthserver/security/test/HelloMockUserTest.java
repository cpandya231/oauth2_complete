package com.govida.security.securityauthserver.security.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HelloMockUserTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    @WithUserDetails("user1@pm..com")
    public void shouldAllowUserWithUserRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello")
                .accept(MediaType.ALL))
                .andExpect(status().isOk());

    }


}