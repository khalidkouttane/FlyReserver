package com.example.checkinservice.model;

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
    private String email;
    private String paymentStatus;

    // ✅ Constructeur vide (important pour JSON, Kafka, etc.)
    public TicketInfo() {}

    // ✅ Constructeur complet
    public TicketInfo(String passengerFirstName, String passengerLastName, String documentNumber,
                      String flightNumber, String departureAirport, String destinationAirport,
                      String departureTime, String gateClosingTime, String seatNumber,
                      String email, String paymentStatus) {
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

    // ✅ Getters
    public String getPassengerFirstName() { return passengerFirstName; }
    public String getPassengerLastName() { return passengerLastName; }
    public String getDocumentNumber() { return documentNumber; }
    public String getFlightNumber() { return flightNumber; }
    public String getDepartureAirport() { return departureAirport; }
    public String getDestinationAirport() { return destinationAirport; }
    public String getDepartureTime() { return departureTime; }
    public String getGateClosingTime() { return gateClosingTime; }
    public String getSeatNumber() { return seatNumber; }
    public String getEmail() { return email; }
    public String getPaymentStatus() { return paymentStatus; }

    // ✅ Setters
    public void setPassengerFirstName(String passengerFirstName) { this.passengerFirstName = passengerFirstName; }
    public void setPassengerLastName(String passengerLastName) { this.passengerLastName = passengerLastName; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public void setDepartureAirport(String departureAirport) { this.departureAirport = departureAirport; }
    public void setDestinationAirport(String destinationAirport) { this.destinationAirport = destinationAirport; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public void setGateClosingTime(String gateClosingTime) { this.gateClosingTime = gateClosingTime; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}
