package com.example.checkinservice.client;

import com.example.checkinservice.model.TicketInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notification-service", url = "http://localhost:8085")
public interface NotificationClient {
    @PostMapping("/notifications/ticket")
    void sendTicketEmail(@RequestBody TicketInfo ticket);
}
