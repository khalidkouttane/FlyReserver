package com.example.notificationservice.service;

import com.example.notificationservice.model.TicketInfo;

public interface NotificationService {
    void sendPaymentStatusEmail(String email, String status);
    void sendTicketEmail(String email, TicketInfo ticketInfo);
    void sendConfirmationEmail(String email, String message); // méthode manquante
}
