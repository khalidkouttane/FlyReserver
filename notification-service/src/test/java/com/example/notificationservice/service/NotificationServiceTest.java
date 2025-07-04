package com.example.notificationservice.service;

import com.example.notificationservice.model.TicketInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private EmailSender emailSender;
    private PDFGenerator pdfGenerator;
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        emailSender = mock(EmailSender.class);
        pdfGenerator = mock(PDFGenerator.class);
        notificationService = new NotificationServiceImpl(emailSender, pdfGenerator);
    }

    @Test
    void testSendPaymentStatusEmail_Success() {
        String email = "test@example.com";
        String status = "SUCCESS";

        notificationService.sendPaymentStatusEmail(email, status);

        verify(emailSender).sendEmail(eq(email), eq("Statut de paiement"),
                eq("Votre paiement a été effectué avec succès."));
    }

    @Test
    void testSendPaymentStatusEmail_Failure() {
        String email = "test@example.com";
        String status = "FAILED";

        notificationService.sendPaymentStatusEmail(email, status);

        verify(emailSender).sendEmail(eq(email), eq("Statut de paiement"),
                eq("Échec du paiement. Veuillez réessayer."));
    }

    @Test
    void testSendConfirmationEmail() {
        String email = "test@example.com";
        String message = "Bienvenue à bord !";

        notificationService.sendConfirmationEmail(email, message);

        verify(emailSender).sendEmail(email, "Confirmation", message);
    }

    @Test
    void testSendTicketEmail() {
        String email = "test@example.com";
        TicketInfo ticketInfo = mock(TicketInfo.class);
        byte[] pdf = new byte[]{1, 2, 3};
        when(pdfGenerator.generateTicketPDF(ticketInfo)).thenReturn(pdf);

        notificationService.sendTicketEmail(email, ticketInfo);

        verify(emailSender).sendEmailWithAttachment(eq(email), eq("Votre billet d'avion"),
                eq("Veuillez trouver ci-joint votre billet."), eq(pdf), eq("ticket.pdf"));
    }
}
