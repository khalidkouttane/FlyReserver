package com.example.searchservice.service;

import com.example.searchservice.model.Flight;
import com.example.searchservice.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public List<Flight> searchFlights(String origin, String destination) {
        return repository.findByOriginAndDestination(origin, destination);
    }
}
