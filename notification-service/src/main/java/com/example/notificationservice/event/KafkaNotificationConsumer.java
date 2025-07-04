package com.example.notificationservice.event;

import com.example.notificationservice.model.TicketInfo;
import com.example.notificationservice.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaNotificationConsumer {

    private final NotificationService notificationService;

    public KafkaNotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "ticket-events", groupId = "notification-group")
    public void consume(PaymentEvent event) {
        String email = event.getEmail();
        String status = event.getStatus();
        TicketInfo ticketInfo = event.getTicketInfo();

        // 1. Envoyer l'e-mail de statut de paiement
        notificationService.sendPaymentStatusEmail(email, status);

        // 2. Si le paiement a réussi, envoyer le ticket
        if ("SUCCESS".equalsIgnoreCase(status)) {
            notificationService.sendTicketEmail(email, ticketInfo);
        }
    }
}
