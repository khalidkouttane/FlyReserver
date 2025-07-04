package com.example.bookingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class BookingResponse {
    private String passengerFirstName;
    private String passengerLastName;
    private String documentNumber;
    private String flightNumber;
    private String departureAirport;
    private String destinationAirport;
    private LocalDateTime departureTime;
    private LocalDateTime gateClosingTime;
    private String seatNumber;
    private String email; // 🆕 Ajout de l'email
    private String status;
    @Id
    private Long id;


}
