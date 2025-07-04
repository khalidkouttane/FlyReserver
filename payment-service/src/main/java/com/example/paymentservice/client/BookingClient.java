package com.example.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "booking-service")
public interface BookingClient {
    @GetMapping("/bookings/by-ref/{ref}")
    BookingResponse getBooking(@PathVariable("ref") String ref);

    @PutMapping("/bookings/status")
    void updateBookingStatus(@RequestParam("bookingRef") String bookingReference,
                             @RequestParam("status") String status);
    @GetMapping("/bookings/by-ref/{ref}")
    BookingResponse getFullBookingByReference(@PathVariable("ref") String ref);
}
