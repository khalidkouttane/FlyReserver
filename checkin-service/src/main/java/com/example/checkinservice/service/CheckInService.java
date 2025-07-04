package com.example.checkinservice.service;

import com.example.checkinservice.client.BookingClient;
import com.example.checkinservice.client.BookingResponse;
import com.example.checkinservice.client.PaymentClient;
import com.example.checkinservice.client.PaymentResponse;
import com.example.checkinservice.model.CheckIn;
import com.example.checkinservice.repository.CheckInRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckInService {
    private final BookingClient bookingClient;
    private final PaymentClient paymentClient;
    private final CheckInRepository checkInRepository;
    public CheckIn performCheckIn(String bookingReference) {
        try {
            log.info("➡️ Démarrage du check-in pour la référence : {}", bookingReference);
            BookingResponse booking = bookingClient.getBookingByReference(bookingReference);
            if (booking == null) {
                log.error("❌ Réservation introuvable pour la référence {}", bookingReference);
                throw new RuntimeException("Réservation introuvable.");
            }
            PaymentResponse payment = paymentClient.getPaymentByBookingReference(bookingReference);
            if (payment == null || !"PAID".equalsIgnoreCase(payment.getStatus())) {
                log.error("❌ Paiement non effectué pour la référence {}", bookingReference);
                throw new RuntimeException("Paiement non effectué.");
            }
            checkInRepository.findByBookingReference(bookingReference).ifPresent(existing -> {
                log.warn("⚠️ Check-in déjà effectué pour {}", bookingReference);
                throw new RuntimeException("Check-in déjà effectué pour cette réservation.");
            });
            CheckIn checkIn = new CheckIn();
            checkIn.setBookingReference(bookingReference);
            checkIn.setCheckedIn(true);
            checkIn.setCheckInTime(LocalDateTime.now());
            CheckIn savedCheckIn = checkInRepository.save(checkIn);
            log.info("✅ Check-in effectué avec succès pour {}", bookingReference);
            return savedCheckIn;
        } catch (Exception e) {
            log.error("🔥 Erreur pendant le check-in pour {} : {}", bookingReference, e.getMessage(), e);
            throw new RuntimeException("Erreur pendant le check-in : " + e.getMessage());
        }
    }
    public List<CheckIn> getAllCheckIns() {
        return checkInRepository.findAll();
    }
}
