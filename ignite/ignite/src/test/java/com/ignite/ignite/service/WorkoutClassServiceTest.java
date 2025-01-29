package com.ignite.ignite.service;

import com.ignite.ignite.request.WorkoutClassCreateRequest;
import com.ignite.ignite.responseModel.WorkoutClass;
import com.ignite.ignite.services.WorkoutClassService;
import com.ignite.ignite.utils.DataUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class WorkoutClassServiceTest {

    @Autowired
    private WorkoutClassService workoutClassService;

    @Test
    @DisplayName("Correct class with valid request")
    void testCreateClass() throws IOException {
        //Arrange
        String request = DataUtil.getCorrectClassRequest();
        String response = DataUtil.getCorrectClassResponse();

        WorkoutClassCreateRequest classCreateRequest = DataUtil.getObjectFromString(request, WorkoutClassCreateRequest.class);
        WorkoutClass classCreateResponse = DataUtil.getObjectFromString(response, WorkoutClass.class);

        //Act
        WorkoutClass createdClass = workoutClassService.createClass(classCreateRequest);

        //Assert
        assertEquals(classCreateResponse.getName(), createdClass.getName());
        assertEquals(classCreateResponse.getStartDate(), createdClass.getStartDate());
        assertEquals(classCreateResponse.getEndDate(), createdClass.getEndDate());
        assertEquals(classCreateResponse.getCapacity(), createdClass.getCapacity());
    }

    @Test
    @DisplayName("Exception with start date after end date")
    void testCreateClass_throwsException() throws IOException {
        //Arrange
        String request = DataUtil.getWrongClassRequest();
        WorkoutClassCreateRequest classCreateRequest = DataUtil.getObjectFromString(request, WorkoutClassCreateRequest.class);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> workoutClassService.createClass(classCreateRequest));
    }
}