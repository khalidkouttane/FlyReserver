package com.example.paymentservice.client;

import lombok.Data;

@Data
public class BookingResponse {
    private String bookingReference;
    private String status;
    private String flightNumber;
    private String departureAirport;
    private String destinationAirport;
    private String departureTime;
    private String gateClosingTime;
    private String seatNumber;

    // ✅ Ce champ manquait
    private Long passengerId;
}
