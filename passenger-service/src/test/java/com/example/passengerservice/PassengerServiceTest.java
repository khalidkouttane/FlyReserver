package com.example.passengerservice;

import com.example.passengerservice.model.Passenger;
import com.example.passengerservice.repository.PassengerRepository;
import com.example.passengerservice.service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PassengerServiceTest {

    private PassengerService passengerService;
    private PassengerRepository passengerRepository;

    @BeforeEach
    void setUp() {
        passengerRepository = mock(PassengerRepository.class);
        passengerService = new PassengerService(passengerRepository);
    }

    @Test
    void testGetAllPassengers() {
        Passenger p1 = new Passenger();
        p1.setFirstName("Alice");
        Passenger p2 = new Passenger();
        p2.setFirstName("Bob");

        when(passengerRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Passenger> result = passengerService.getAllPassengers();

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getFirstName());
    }

    @Test
    void testGetPassengerById() {
        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setEmail("test@example.com");

        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passenger));

        Optional<Passenger> result = passengerService.getPassengerById(1L);

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
    }

    @Test
    void testCreatePassenger() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Test");

        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);

        Passenger saved = passengerService.createPassenger(passenger);

        assertEquals("Test", saved.getFirstName());
        verify(passengerRepository, times(1)).save(passenger);
    }

    @Test
    void testDeletePassenger() {
        Long id = 99L;

        passengerService.deletePassenger(id);

        verify(passengerRepository, times(1)).deleteById(id);
    }
}
