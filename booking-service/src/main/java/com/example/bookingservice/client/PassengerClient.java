package com.example.bookingservice.client;

import com.example.bookingservice.client.PassengerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PassengerClient {

    private final RestTemplate restTemplate;
    private final String passengerServiceUrl;

    public PassengerClient(RestTemplate restTemplate,
                           @Value("${passenger.service.url}") String passengerServiceUrl) {
        this.restTemplate = restTemplate;
        this.passengerServiceUrl = passengerServiceUrl;
    }

    public PassengerResponse getPassengerById(Long id) {
        String url = passengerServiceUrl + "/passengers/" + id;
        return restTemplate.getForObject(url, PassengerResponse.class);
    }
}
