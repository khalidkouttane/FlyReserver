package com.example.bookingservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookingReference;
    private Long passengerId; // 🔁 Nouveau champ
    private String flightNumber;
    private String departureAirport;
    private String destinationAirport;
    private LocalDateTime departureTime;
    private LocalDateTime gateClosingTime;
    private String seatNumber;
    private String status;
    public Booking() {}
    public Booking(Long id, String bookingReference, Long passengerId, String flightNumber,
                   String departureAirport, String destinationAirport, LocalDateTime departureTime,
                   String seatNumber, LocalDateTime gateClosingTime, String status) {
        this.id = id;
        this.bookingReference = bookingReference;
        this.passengerId = passengerId;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.seatNumber = seatNumber;
        this.gateClosingTime = gateClosingTime;
        this.status = status;
    }

}
