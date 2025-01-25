package com.ignite.ignite.service;


import com.ignite.ignite.responseModel.Booking;
import com.ignite.ignite.services.BookingService;
import com.ignite.ignite.services.WorkoutClassService;
import com.ignite.ignite.utils.DataUtil;
import com.ignite.ignite.request.BookingCreateRequest;
import com.ignite.ignite.request.ClassCreateRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private WorkoutClassService workoutClassService;

    @BeforeAll
    void setUp() throws IOException {
        String request = DataUtil.getCorrectClassRequest();
        ClassCreateRequest classCreateRequest = DataUtil.getObjectFromString(request, ClassCreateRequest.class);
        workoutClassService.createClass(classCreateRequest);
    }

    @Test
    @DisplayName("Correct create booking request executed")
    void testBookClass() throws IOException {
        //Arrange
        String request = DataUtil.getCorrectBookingRequest();
        String response = DataUtil.getCorrectBookingResponse();

        BookingCreateRequest bookingCreateRequest = DataUtil.getObjectFromString(request, BookingCreateRequest.class);
        Booking bookingCreateResponse = DataUtil.getObjectFromString(response, Booking.class);
        //Act
        Booking createdBooking = bookingService.createBooking(bookingCreateRequest);
        //Assert
        assertEquals(bookingCreateResponse.getClassName(), createdBooking.getClassName());
        assertEquals(bookingCreateResponse.getMemberName(), createdBooking.getMemberName());
        assertEquals(bookingCreateResponse.getParticipationDate(), createdBooking.getParticipationDate());
    }

    @Test
    @DisplayName("Exception with class not found")
    void testBookClass_throwsExceptionIfClassDoesNotExist() throws IOException {
        //Arrange
        String request = DataUtil.getWrongBookingRequest();
        BookingCreateRequest bookingCreateRequest = DataUtil.getObjectFromString(request, BookingCreateRequest.class);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(bookingCreateRequest));
    }
}