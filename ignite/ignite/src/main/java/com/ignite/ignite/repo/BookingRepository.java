package com.ignite.ignite.repo;

import com.ignite.ignite.responseModel.Booking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingRepository {
    private List<Booking> bookings = new ArrayList<>();

    public Booking save(Booking booking) {
        bookings.add(booking);
        return booking;
    }

    public List<Booking> findAll() {
        return bookings;
    }
}
