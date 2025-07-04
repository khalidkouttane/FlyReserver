package com.example.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "passenger-service")
public interface PassengerClient {

    @GetMapping("/passengers/{id}")
    PassengerResponse getPassengerById(@PathVariable("id") Long id);
}
