package io.github.eliassink.bankaccount;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testContent() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("/home.html"));

        mockMvc.perform(get("/home.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}