package com.example.hospital.controllers;

import com.example.hospital.dto.NotificationDto;
import com.example.hospital.services.observer.notifications.NotificationServiceManager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationServiceManager notificationServiceManager;

    public NotificationController(NotificationServiceManager notificationServiceManager) {
        this.notificationServiceManager = notificationServiceManager;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> notifyAllObservers(@RequestBody NotificationDto request) {
        notificationServiceManager.notifyObservers(request.getMessage(), request.getSubject());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Notification sent to all subscribed observers.");

        return ResponseEntity.ok(response);
    }
}
