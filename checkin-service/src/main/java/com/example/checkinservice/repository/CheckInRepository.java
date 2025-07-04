package com.example.checkinservice.repository;

import com.example.checkinservice.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    Optional<CheckIn> findByBookingReference(String bookingReference);
}
