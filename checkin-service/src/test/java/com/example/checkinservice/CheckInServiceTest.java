package com.example.checkinservice;

import com.example.checkinservice.client.BookingClient;
import com.example.checkinservice.client.PaymentClient;
import com.example.checkinservice.model.CheckIn;
import com.example.checkinservice.client.PaymentResponse;
import com.example.checkinservice.client.BookingResponse;
import com.example.checkinservice.repository.CheckInRepository;
import com.example.checkinservice.service.CheckInService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CheckInServiceTest {

    private BookingClient bookingClient;
    private PaymentClient paymentClient;
    private CheckInRepository checkInRepository;
    private CheckInService checkInService;

    @BeforeEach
    void setup() {
        bookingClient = mock(BookingClient.class);
        paymentClient = mock(PaymentClient.class);
        checkInRepository = mock(CheckInRepository.class);
        checkInService = new CheckInService(bookingClient, paymentClient, checkInRepository);
    }

    @Test
    void testPerformCheckIn_Success() {
        String bookingRef = "ABC123";

        BookingResponse booking = new BookingResponse();
        booking.setBookingReference(bookingRef);
        booking.setPassengerId("1");

        PaymentResponse payment = new PaymentResponse();
        payment.setStatus("PAID");

        when(bookingClient.getBookingByReference(bookingRef)).thenReturn(booking);
        when(paymentClient.getPaymentByBookingReference(bookingRef)).thenReturn(payment);
        when(checkInRepository.findByBookingReference(bookingRef)).thenReturn(Optional.empty());

        CheckIn saved = new CheckIn();
        saved.setBookingReference(bookingRef);
        saved.setCheckedIn(true);
        saved.setCheckInTime(LocalDateTime.now());

        when(checkInRepository.save(any(CheckIn.class))).thenReturn(saved);

        CheckIn result = checkInService.performCheckIn(bookingRef);

        assertNotNull(result);
        assertTrue(result.isCheckedIn());
        assertEquals(bookingRef, result.getBookingReference());
    }

    @Test
    void testPerformCheckIn_AlreadyCheckedIn() {
        String bookingRef = "XYZ999";

        BookingResponse booking = new BookingResponse();
        booking.setBookingReference(bookingRef);
        booking.setPassengerId("2L");

        PaymentResponse payment = new PaymentResponse();
        payment.setStatus("PAID");

        CheckIn existingCheckIn = new CheckIn();
        existingCheckIn.setBookingReference(bookingRef);

        when(bookingClient.getBookingByReference(bookingRef)).thenReturn(booking);
        when(paymentClient.getPaymentByBookingReference(bookingRef)).thenReturn(payment);
        when(checkInRepository.findByBookingReference(bookingRef)).thenReturn(Optional.of(existingCheckIn));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            checkInService.performCheckIn(bookingRef);
        });

        assertTrue(exception.getMessage().contains("Check-in déjà effectué"));
    }

    @Test
    void testPerformCheckIn_BookingNotFound() {
        String bookingRef = "NOTFOUND";

        when(bookingClient.getBookingByReference(bookingRef)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            checkInService.performCheckIn(bookingRef);
        });

        assertTrue(exception.getMessage().contains("Réservation introuvable"));
    }
    @Test
    void testPerformCheckIn_PaymentNotMade() {
        String bookingRef = "NOPAY";

        BookingResponse booking = new BookingResponse();
        booking.setBookingReference(bookingRef);
        booking.setPassengerId("3L");

        PaymentResponse payment = new PaymentResponse();
        payment.setStatus("FAILED");

        when(bookingClient.getBookingByReference(bookingRef)).thenReturn(booking);
        when(paymentClient.getPaymentByBookingReference(bookingRef)).thenReturn(payment);
        when(checkInRepository.findByBookingReference(bookingRef)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            checkInService.performCheckIn(bookingRef);
        });

        assertTrue(exception.getMessage().contains("Paiement non effectué"));
    }
}
