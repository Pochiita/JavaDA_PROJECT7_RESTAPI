package com.nnk.springboot.Controller;

/*
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void loginWithValidUser() throws Exception {
        String password = passwordEncoder.encode("test");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("username", "test")
                        .param("password", password))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void loginWithInvalidUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("username", "invaliduser")
                        .param("password", "invalidpassword"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}
*/