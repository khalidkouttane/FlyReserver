package com.example.bookingservice.client;

import lombok.Data;



@Data
public class PassengerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String documentNumber;
}