package com.example.checkinservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway", url = "http://localhost:8080")
public interface PaymentClient {

    @GetMapping("/payment-service/payments/by-booking/{bookingReference}")
    PaymentResponse getPaymentByBookingReference(@PathVariable String bookingReference);
}
