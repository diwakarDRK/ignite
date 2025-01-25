package com.ignite.ignite.tests;

import com.ignite.ignite.utils.DataUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ClassApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void testBookClass() throws Exception {
        //Arrange
        String request = DataUtil.getCorrectClassRequest();
        String response = DataUtil.getCorrectClassResponse();
        mockMvc.perform(MockMvcRequestBuilders.post("/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(content().json(response))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void testBookClass_throwsException() throws Exception {
        //Arrange
        String request = DataUtil.getCorrectClassRequest();
        String response = DataUtil.getBadRequestClassResponse();
        mockMvc.perform(MockMvcRequestBuilders.post("/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(content().json(response))
                .andExpect(status().isBadRequest());
    }
}
