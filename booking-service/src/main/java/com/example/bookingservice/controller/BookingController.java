package com.example.bookingservice.controller;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.model.BookingRequest;
import com.example.bookingservice.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
    // 👉 Recherche par ID (Long)
    @GetMapping("/by-id/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/by-ref/{ref}")
    public ResponseEntity<Booking> getByRef(@PathVariable String ref) {
        return bookingService.getBookingByReference(ref)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = new Booking();
        booking.setPassengerId(bookingRequest.getPassengerId());
        booking.setFlightNumber(bookingRequest.getFlightNumber());
        booking.setDepartureAirport(bookingRequest.getDepartureAirport());
        booking.setDestinationAirport(bookingRequest.getDestinationAirport());
        booking.setDepartureTime(bookingRequest.getDepartureTime());
        booking.setGateClosingTime(bookingRequest.getGateClosingTime());
        booking.setSeatNumber(bookingRequest.getSeatNumber());
        booking.setStatus(bookingRequest.getStatus());

        return ResponseEntity.ok(bookingService.createBooking(booking));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/status")
    public ResponseEntity<String> updateBookingStatus(@RequestParam String bookingRef, @RequestParam String status) {
        bookingService.updateStatus(bookingRef, status);
        return ResponseEntity.ok("Status updated");
    }
}
