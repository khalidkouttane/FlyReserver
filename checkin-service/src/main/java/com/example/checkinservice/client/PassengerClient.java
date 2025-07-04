package com.example.checkinservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "passenger-service", url = "http://localhost:8080")
public interface PassengerClient {
    @GetMapping("/passenger-service/passengers/{id}")
    PassengerResponse getPassengerById(@PathVariable String id);
}
