package com.example.checkinservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service")
public interface BookingClient {

    @GetMapping("/bookings/by-ref/{ref}")
    BookingResponse getBookingByReference(@PathVariable("ref") String bookingReference);
}
