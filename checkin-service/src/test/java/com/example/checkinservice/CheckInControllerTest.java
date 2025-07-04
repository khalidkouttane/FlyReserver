package com.example.checkinservice;

import com.example.checkinservice.controller.CheckInController;
import com.example.checkinservice.model.CheckIn;
import com.example.checkinservice.service.CheckInService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CheckInController.class)
public class CheckInControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckInService checkInService;

    @Test
    void testPerformCheckInEndpoint() throws Exception {
        String bookingRef = "ABC123";

        CheckIn checkIn = new CheckIn();
        checkIn.setBookingReference(bookingRef);
        checkIn.setCheckedIn(true);
        checkIn.setCheckInTime(LocalDateTime.now());

        when(checkInService.performCheckIn(bookingRef)).thenReturn(checkIn);

        mockMvc.perform(post("/api/checkins/{bookingRef}", bookingRef))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingReference").value(bookingRef))
                .andExpect(jsonPath("$.checkedIn").value(true));
    }

    @Test
    void testGetAllCheckInsEndpoint() throws Exception {
        CheckIn checkIn = new CheckIn();
        checkIn.setBookingReference("XYZ999");
        checkIn.setCheckedIn(true);
        checkIn.setCheckInTime(LocalDateTime.now());

        when(checkInService.getAllCheckIns()).thenReturn(Collections.singletonList(checkIn));

        mockMvc.perform(get("/api/checkins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].bookingReference").value("XYZ999"));
    }
}
