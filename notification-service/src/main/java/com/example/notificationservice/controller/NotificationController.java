package com.example.notificationservice.controller;

import com.example.notificationservice.model.TicketInfo;
import com.example.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/confirm")
    public void sendConfirmation(@RequestParam String email, @RequestBody String message) {
        notificationService.sendConfirmationEmail(email, message);
    }

    @PostMapping("/ticket")
    public void sendTicket(@RequestParam String email, @RequestBody TicketInfo ticketInfo) {
        notificationService.sendTicketEmail(email, ticketInfo);
    }
}
