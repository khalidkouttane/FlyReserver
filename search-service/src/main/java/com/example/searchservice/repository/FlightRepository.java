package com.example.searchservice.repository;

import com.example.searchservice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByOriginAndDestination(String origin, String destination);
}
