package com.example.checkinservice.client;

import lombok.Data;

@Data
public class BookingResponse {
    private String passengerId; // 🔴 obligatoire
    private String bookingReference;
    private String flightNumber;
    private String departureAirport;
    private String destinationAirport;
    private String departureTime;
    private String gateClosingTime;
    private String seatNumber;
}