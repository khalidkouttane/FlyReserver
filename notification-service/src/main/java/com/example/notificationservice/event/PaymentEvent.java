package com.example.notificationservice.event;

import com.example.notificationservice.model.TicketInfo;

public class PaymentEvent {
    private String status;
    private String email;
    private TicketInfo ticketInfo;

    public PaymentEvent() {}

    public PaymentEvent(String status, String email, TicketInfo ticketInfo) {
        this.status = status;
        this.email = email;
        this.ticketInfo = ticketInfo;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public TicketInfo getTicketInfo() { return ticketInfo; }
    public void setTicketInfo(TicketInfo ticketInfo) { this.ticketInfo = ticketInfo; }
}
