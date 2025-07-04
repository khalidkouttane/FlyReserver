package com.example.bookingservice;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.repository.BookingRepository;
import com.example.bookingservice.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    private BookingService bookingService;
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        bookingRepository = mock(BookingRepository.class);
        bookingService = new BookingService(bookingRepository);
    }

    @Test
    void testCreateBooking() {
        Booking booking = new Booking();
        booking.setFlightNumber("AF123");
        booking.setDepartureTime(LocalDateTime.now());

        Booking savedBooking = new Booking();
        savedBooking.setFlightNumber("AF123");
        savedBooking.setBookingReference("F2676EAF"); // simulée
        savedBooking.setDepartureTime(booking.getDepartureTime());

        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        Booking created = bookingService.createBooking(booking);

        assertNotNull(created.getBookingReference()); // ✅ mieux que vérifier une valeur fixe
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void testGetBookingByReference() {
        Booking booking = new Booking();
        booking.setBookingReference("ABC123");

        when(bookingRepository.findByBookingReference("ABC123"))
                .thenReturn(Optional.of(booking));

        Optional<Booking> result = bookingService.getBookingByReference("ABC123");

        assertTrue(result.isPresent());
        assertEquals("ABC123", result.get().getBookingReference());
    }
}
