package com.example.notificationservice.model;

public class TicketInfo {
    private String passengerFirstName;
    private String passengerLastName;
    private String documentNumber;
    private String flightNumber;
    private String departureAirport;
    private String destinationAirport;
    private String departureTime;
    private String gateClosingTime;
    private String seatNumber;
    private String email; // 🆕
    private String paymentStatus; // 🆕
    public String getPassengerFirstName() { return passengerFirstName; }
    public String getPassengerLastName() { return passengerLastName; }
    public String getDocumentNumber() { return documentNumber; }
    public String getFlightNumber() { return flightNumber; }
    public String getDepartureAirport() { return departureAirport; }
    public String getDestinationAirport() { return destinationAirport; }
    public String getDepartureTime() { return departureTime; }
    public String getGateClosingTime() { return gateClosingTime; }
    public String getSeatNumber() { return seatNumber; }
    public String getEmail() { return email; } // 🆕
    public String getPaymentStatus() { return paymentStatus; } // 🆕
    public TicketInfo() {}
    public TicketInfo(String passengerFirstName, String passengerLastName, String documentNumber, String flightNumber,
                      String departureAirport, String destinationAirport, String departureTime,
                      String gateClosingTime, String seatNumber, String email, String paymentStatus) {
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.documentNumber = documentNumber;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.gateClosingTime = gateClosingTime;
        this.seatNumber = seatNumber;
        this.email = email;
        this.paymentStatus = paymentStatus;
    }
}
