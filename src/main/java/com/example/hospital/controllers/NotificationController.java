package com.example.hospital.controllers;

import com.example.hospital.services.observer.notifications.NotificationServiceManager;
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
    public ResponseEntity<String> notifyAllObservers(@RequestParam String message) {
        notificationServiceManager.notifyObservers(message);
        return ResponseEntity.ok("Notification sent to all subscribed observers.");
    }
}
