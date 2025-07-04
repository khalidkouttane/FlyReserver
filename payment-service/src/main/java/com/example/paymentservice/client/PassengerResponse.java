package com.example.paymentservice.client;

import lombok.Data;

@Data
public class PassengerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String email;
}
