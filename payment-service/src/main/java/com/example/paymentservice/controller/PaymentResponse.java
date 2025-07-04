package com.example.paymentservice.controller;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentResponse {
    // Getters & Setters
    private String bookingReference;
    private String status;
    private String email;

}

