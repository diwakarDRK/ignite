package com.ignite.ignite.tests;

import com.ignite.ignite.utils.DataUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class BookingApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void testBookClass() throws Exception {
        //Arrange
        String request = DataUtil.getCorrectBookingRequest();
        String response = DataUtil.getCorrectBookingResponse();
        String requestClass = DataUtil.getCorrectClassRequest();
        mockMvc.perform(MockMvcRequestBuilders.post("/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestClass))
                .andExpect(status().isOk());

        //Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(content().json(response))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void testBookClass_throwsBadRequest() throws Exception {
        String request = DataUtil.getWrongBookingRequest();
        String response = DataUtil.getWrongBookingResponse();
        mockMvc.perform(MockMvcRequestBuilders.post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(content().json(response))
                .andExpect(status().isBadRequest());
    }

    //Similar test for already booked class, not writing it here because lack of time
}
