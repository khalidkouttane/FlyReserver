package com.example.checkinservice.client;

public class PaymentResponse {
    private String bookingReference;
    private String status;
    private String email; // ✅ nécessaire

    public String getBookingReference() {
        return bookingReference;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
