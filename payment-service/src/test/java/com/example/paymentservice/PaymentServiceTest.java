package com.example.paymentservice;

import com.example.paymentservice.client.BookingClient;
import com.example.paymentservice.client.BookingResponse;
import com.example.paymentservice.client.PassengerClient;
import com.example.paymentservice.client.PassengerResponse;
import com.example.paymentservice.event.KafkaPaymentProducer;
import com.example.paymentservice.event.PaymentEvent;
import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private BookingClient bookingClient;

    @Mock
    private PassengerClient passengerClient;

    @Mock
    private KafkaPaymentProducer kafkaPaymentProducer;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void testProcessPayment_Success() {
        // GIVEN
        Payment payment = new Payment();
        payment.setBookingReference("BOOK123");
        payment.setEmail("passenger@example.com");

        BookingResponse booking = new BookingResponse();
        booking.setBookingReference("BOOK123");
        booking.setStatus("PENDING");
        booking.setPassengerId(1L);
        booking.setDepartureAirport("CDG");
        booking.setDestinationAirport("JFK");
        booking.setDepartureTime("2025-07-01T10:00:00");
        booking.setGateClosingTime("2025-07-01T09:30:00");
        booking.setSeatNumber("12A");

        PassengerResponse passenger = new PassengerResponse();
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setEmail("passenger@example.com");
        passenger.setDocumentNumber("XYZ123");

        when(bookingClient.getFullBookingByReference("BOOK123")).thenReturn(booking);
        when(passengerClient.getPassengerById(1L)).thenReturn(passenger);
        when(paymentRepository.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));

        // WHEN
        Payment result = paymentService.processPayment(payment);

        // THEN
        assertEquals("PAID", result.getStatus());
        assertEquals("BOOK123", result.getBookingReference());
        verify(kafkaPaymentProducer, times(1)).sendTicketEvent(any(PaymentEvent.class));
    }

    @Test
    void testGetPaymentByBookingReference() {
        Payment payment = new Payment();
        payment.setBookingReference("BOOK456");

        when(paymentRepository.findByBookingReference("BOOK456")).thenReturn(payment);

        Payment result = paymentService.getPaymentByBookingReference("BOOK456");

        assertEquals("BOOK456", result.getBookingReference());
    }
}