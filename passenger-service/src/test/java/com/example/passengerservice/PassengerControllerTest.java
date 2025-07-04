package com.example.passengerservice;

import com.example.passengerservice.controller.PassengerController;
import com.example.passengerservice.model.Passenger;
import com.example.passengerservice.service.PassengerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PassengerController.class)
public class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetPassengerById() throws Exception {
        Passenger passenger = new Passenger("John", "Doe", "john@example.com", "123456789", "ABC123");
        when(passengerService.getPassengerById(1L)).thenReturn(Optional.of(passenger));

        mockMvc.perform(get("/passengers/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePassenger() throws Exception {
        Passenger passenger = new Passenger("Jane", "Smith", "jane@example.com", "987654321", "XYZ789");
        when(passengerService.createPassenger(passenger)).thenReturn(passenger);

        mockMvc.perform(post("/passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passenger)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePassenger() throws Exception {
        mockMvc.perform(delete("/passengers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllPassengers() throws Exception {
        Passenger p1 = new Passenger("A", "B", "a@example.com", "123", "AAA");
        Passenger p2 = new Passenger("C", "D", "c@example.com", "456", "BBB");
        when(passengerService.getAllPassengers()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/passengers"))
                .andExpect(status().isOk());
    }
}
