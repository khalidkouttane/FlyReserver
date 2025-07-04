package com.example.notificationservice.service;

import com.example.notificationservice.model.TicketInfo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Component
@Service
public class PDFGenerator {
    public byte[] generateTicketPDF(TicketInfo ticketInfo) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("Ticket de vol"));
            document.add(new Paragraph("Nom: " + ticketInfo.getPassengerFirstName() + " " + ticketInfo.getPassengerLastName()));
            document.add(new Paragraph("Document: " + ticketInfo.getDocumentNumber()));
            document.add(new Paragraph("Vol: " + ticketInfo.getFlightNumber()));
            document.add(new Paragraph("De: " + ticketInfo.getDepartureAirport() + " à " + ticketInfo.getDestinationAirport()));
            document.add(new Paragraph("Départ: " + ticketInfo.getDepartureTime()));
            document.add(new Paragraph("Fermeture porte: " + ticketInfo.getGateClosingTime()));
            document.add(new Paragraph("Siège: " + ticketInfo.getSeatNumber()));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}