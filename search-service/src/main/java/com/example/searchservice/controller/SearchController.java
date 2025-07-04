package com.example.searchservice.controller;

import com.example.searchservice.model.Flight;
import com.example.searchservice.service.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final FlightService flightService;

    public SearchController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public List<Flight> searchFlights(@RequestParam String origin, @RequestParam String destination) {
        return flightService.searchFlights(origin, destination);
    }
}