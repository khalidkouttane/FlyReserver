package com.example.paymentservice.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketInfo implements Serializable {
    private String passengerFirstName;
    private String passengerLastName;
    private String documentNumber;
    private String flightNumber;
    private String departureAirport;
    private String destinationAirport;
    private String departureTime;
    private String gateClosingTime;
    private String seatNumber;
    private String email;
    private String status;
}
