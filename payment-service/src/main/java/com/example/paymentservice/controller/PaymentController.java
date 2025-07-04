package com.example.paymentservice.controller;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/pay")
    public Payment pay(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }

    @GetMapping("/{bookingRef}")
    public Payment getPayment(@PathVariable String bookingRef) {
        return paymentService.getPaymentByBookingReference(bookingRef);
    }

    // ✅ AJOUT : retourne la réponse enrichie avec l'email
    @GetMapping("/by-booking/{ref}")
    public PaymentResponse getPaymentByBookingReference(@PathVariable String ref) {
        Payment payment = paymentRepository.findByBookingReference(ref);
        if (payment == null) return null;

        PaymentResponse response = new PaymentResponse();
        response.setBookingReference(payment.getBookingReference());
        response.setStatus(payment.getStatus());
        response.setEmail(payment.getEmail()); // 🔥 très important pour le check-in
        return response;
    }
}
