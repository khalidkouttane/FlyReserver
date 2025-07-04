package com.example.bookingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@Entity
public class BookingRequest {
    private Long passengerId;
    private String flightNumber;
    private String departureAirport;
    private String destinationAirport;
    private LocalDateTime departureTime;
    private LocalDateTime gateClosingTime;
    private String seatNumber;
    private String status;
    @Id
    private Long id;

}
