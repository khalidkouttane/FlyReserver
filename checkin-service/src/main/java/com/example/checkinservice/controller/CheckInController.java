package com.example.checkinservice.controller;

import com.example.checkinservice.model.CheckIn;
import com.example.checkinservice.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @PostMapping("/{bookingRef}")
    public CheckIn performCheckIn(@PathVariable String bookingRef) {
        return checkInService.performCheckIn(bookingRef);
    }

    @GetMapping
    public List<CheckIn> getAllCheckIns() {
        return checkInService.getAllCheckIns();
    }
}
