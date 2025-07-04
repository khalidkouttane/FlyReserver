package com.example.paymentservice.service;

import com.example.paymentservice.client.BookingClient;
import com.example.paymentservice.client.BookingResponse;
import com.example.paymentservice.client.PassengerClient;
import com.example.paymentservice.client.PassengerResponse;
import com.example.paymentservice.client.TicketInfo;
import com.example.paymentservice.event.KafkaPaymentProducer;
import com.example.paymentservice.event.PaymentEvent;
import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingClient bookingClient;
    @Autowired
    private PassengerClient passengerClient;
    @Autowired
    private KafkaPaymentProducer kafkaPaymentProducer;
    public Payment processPayment(Payment payment) {
        BookingResponse booking = bookingClient.getFullBookingByReference(payment.getBookingReference());
        if (booking == null || !"PENDING".equals(booking.getStatus())) {
            throw new RuntimeException("Réservation non valide ou déjà payée !");
        }
        PassengerResponse passenger = passengerClient.getPassengerById(booking.getPassengerId());
        payment.setStatus("PAID");
        Payment savedPayment = paymentRepository.save(payment);
        TicketInfo ticketInfo = new TicketInfo(
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getDocumentNumber(),
                booking.getFlightNumber(),
                booking.getDepartureAirport(),
                booking.getDestinationAirport(),
                booking.getDepartureTime().toString(),
                booking.getGateClosingTime().toString(),
                booking.getSeatNumber(),
                passenger.getEmail(),
                "SUCCESS"
        );
        PaymentEvent event = new PaymentEvent("SUCCESS", passenger.getEmail(), ticketInfo);
        kafkaPaymentProducer.sendTicketEvent(event);
        return savedPayment;
    }
    public Payment getPaymentByBookingReference(String bookingRef) {
        return paymentRepository.findByBookingReference(bookingRef);
    }
}
