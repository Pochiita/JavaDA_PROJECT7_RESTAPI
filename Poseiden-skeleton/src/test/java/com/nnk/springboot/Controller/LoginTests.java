package com.nnk.springboot.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

@SpringBootTest
public class LoginTests {

    @Test
    @WithUserDetails("test@test.com")
    public void checkLoginController (){
        mockMvc.perform(formLogin().user("test@test.com").password("password"))
                .andExpect(authenticated().withUsername("test@test.com"))
                .andExpect(redirectedUrl("/bidList/list"));
    }

}
