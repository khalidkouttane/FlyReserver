package com.example.bookingservice.service;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Optional<Booking> getBookingByReference(String ref) {
        return bookingRepository.findByBookingReference(ref);
    }

    public Booking createBooking(Booking booking) {
        booking.setBookingReference(UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        if (booking.getStatus() == null || booking.getStatus().isEmpty()) {
            booking.setStatus("PENDING");
        }

        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public void updateStatus(String bookingRef, String newStatus) {
        Optional<Booking> optionalBooking = bookingRepository.findByBookingReference(bookingRef);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setStatus(newStatus);
            bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with reference: " + bookingRef);
        }
    }
}
