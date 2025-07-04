package com.example.notificationservice.service;

import com.example.notificationservice.model.TicketInfo;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final EmailSender emailSender;
    private final PDFGenerator pdfGenerator;

    public NotificationServiceImpl(EmailSender emailSender, PDFGenerator pdfGenerator) {
        this.emailSender = emailSender;
        this.pdfGenerator = pdfGenerator;
    }

    @Override
    public void sendPaymentStatusEmail(String email, String status) {
        String message;
        if ("SUCCESS".equalsIgnoreCase(status)) {
            message = "Votre paiement a été effectué avec succès.";
        } else {
            message = "Échec du paiement. Veuillez réessayer.";
        }
        emailSender.sendEmail(email, "Statut de paiement", message);
    }

    @Override
    public void sendTicketEmail(String email, TicketInfo ticketInfo) {
        byte[] pdfBytes = pdfGenerator.generateTicketPDF(ticketInfo);
        emailSender.sendEmailWithAttachment(email, "Votre billet d'avion", "Veuillez trouver ci-joint votre billet.", pdfBytes, "ticket.pdf");
    }

    @Override
    public void sendConfirmationEmail(String email, String message) {
        // Implémentation de secours ou de confirmation générique
        emailSender.sendEmail(email, "Confirmation", message);
    }
}
