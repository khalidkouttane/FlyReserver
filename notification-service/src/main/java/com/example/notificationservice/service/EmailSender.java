package com.example.notificationservice.service;

public interface EmailSender {
    void sendEmail(String to, String subject, String body);
    void sendEmailWithAttachment(String to, String subject, String body, byte[] attachment, String filename);
}
