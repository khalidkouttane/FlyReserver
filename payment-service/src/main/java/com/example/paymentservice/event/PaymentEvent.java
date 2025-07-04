package com.example.paymentservice.event;

import com.example.paymentservice.client.TicketInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private String status; // "SUCCESS" ou "FAILED"
    private String email;  // ✨ email récupéré via passenger-service
    private TicketInfo ticketInfo;
}
