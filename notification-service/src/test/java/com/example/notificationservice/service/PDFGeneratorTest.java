package com.example.notificationservice.service;

import com.example.notificationservice.model.TicketInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PDFGeneratorTest {

    private final PDFGenerator pdfGenerator = new PDFGenerator();

    @Test
    void testGenerateTicketPDF_NotNullAndNotEmpty() {
        TicketInfo ticketInfo = new TicketInfo(
                "Khalid",
                "El Youssfi",
                "AB123456",
                "AT123",
                "CMN",
                "CDG",
                "12:00",
                "11:30",
                "14A",
                "khalid@example.com",
                "SUCCESS"
        );

        byte[] pdfBytes = pdfGenerator.generateTicketPDF(ticketInfo);

        assertNotNull(pdfBytes, "Le PDF généré ne doit pas être null.");
        assertTrue(pdfBytes.length > 0, "Le PDF généré doit contenir des données.");
    }
}
