package com.ignite.ignite.services;

import com.ignite.ignite.responseModel.Booking;
import com.ignite.ignite.responseModel.WorkoutClass;
import com.ignite.ignite.repo.BookingRepository;
import com.ignite.ignite.repo.WorkoutClassRepository;
import com.ignite.ignite.request.BookingCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private WorkoutClassRepository classRepository;

    public Booking createBooking(BookingCreateRequest request) {
        // Find matching class
        WorkoutClass matchingClass = classRepository.findAll().stream()
                .filter(c -> c.getName().equals(request.getClassName()) &&
                        !request.getParticipationDate().isBefore(c.getStartDate()) &&
                        !request.getParticipationDate().isAfter(c.getEndDate()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching class found"));

        // Create booking
        Booking booking = new Booking();
        booking.setMemberName(request.getMemberName());
        booking.setClassName(request.getClassName());
        booking.setParticipationDate(request.getParticipationDate());

        // Add booking to class
        matchingClass.addBooking(booking);

        return bookingRepository.save(booking);
    }

    public List<Booking> searchBookings(String memberName, LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findAll().stream()
                .filter(b -> (memberName == null || b.getMemberName().equals(memberName)) &&
                        (startDate == null || !b.getParticipationDate().isBefore(startDate)) &&
                        (endDate == null || !b.getParticipationDate().isAfter(endDate)))
                .collect(Collectors.toList());
    }
}
