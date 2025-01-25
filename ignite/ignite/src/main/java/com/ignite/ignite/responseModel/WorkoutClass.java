package com.ignite.ignite.responseModel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class WorkoutClass {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private int durationMinutes;
    private int capacity;
    private List<Booking> bookings;

    public WorkoutClass() {
        this.id = UUID.randomUUID().toString();
        this.bookings = new ArrayList<>();
    }

    public boolean isFullyBooked() {
        return bookings.size() >= capacity;
    }

    public void addBooking(Booking booking) {
        if (!isFullyBooked()) {
            bookings.add(booking);
        } else {
            throw new IllegalStateException("Class is fully booked");
        }
    }
}
