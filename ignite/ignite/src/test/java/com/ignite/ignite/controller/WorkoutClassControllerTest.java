package com.ignite.ignite.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.ignite.ignite.controllers.WorkoutClassController;
import com.ignite.ignite.responseModel.WorkoutClass;
import com.ignite.ignite.services.WorkoutClassService;
import com.ignite.ignite.utils.DataUtil;
import com.ignite.ignite.request.WorkoutClassCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkoutClassController.class)
class WorkoutClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    WorkoutClassService workoutClassService;


    @Test
    @DisplayName("When createClass() is called with correct parameters, then the class should be created")
    void testWorkoutCreateClass() throws Exception {
        // Arrange
        String request = DataUtil.getCorrectClassRequest();
        String response = DataUtil.getCorrectClassResponse();
        JsonNode responseModel = DataUtil.getObjectFromString(response, JsonNode.class);
        WorkoutClassCreateRequest classRequest = DataUtil.getObjectFromString(request, WorkoutClassCreateRequest.class);

        when(workoutClassService.createClass(classRequest)).thenReturn
                (DataUtil.getObjectFromString(responseModel.get("data").toString(), WorkoutClass.class));
        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(content().json(response))
                .andExpect(status().isOk());
        // Assert
        verify(workoutClassService, times(1)).createClass(classRequest);
        //We can add argument captors as well but this seems thorough enough
    }


    @Test
    @DisplayName("When createClass() is called with correct parameters, then the class should not be created")
    void testCreateClass_withTheSameRequestAgain_throwsBadRequest() throws Exception {
        // Arrange
        String request = DataUtil.getCorrectClassRequest();
        String response = DataUtil.getBadRequestClassResponse();
        WorkoutClassCreateRequest classRequest = DataUtil.getObjectFromString(request, WorkoutClassCreateRequest.class);

        when(workoutClassService.createClass(classRequest)).thenThrow(
                new IllegalArgumentException("A class with these details already exists"));
        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(content().json(response))
                .andExpect(status().is4xxClientError());
        // Assert
        verify(workoutClassService, times(1)).createClass(classRequest);
        //We can add argument captors as well but this seems thorough enough
    }
}