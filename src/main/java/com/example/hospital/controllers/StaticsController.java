package com.example.hospital.controllers;

import com.example.hospital.models.NotificationService;
import com.example.hospital.models.Role;
import com.example.hospital.models.enums.Speciality;
import com.example.hospital.services.StaticsService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statics")
public class StaticsController {

    private final StaticsService staticsService;

    public StaticsController(StaticsService staticsService) {
        this.staticsService = staticsService;
    }

    @GetMapping("/get-all-roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = staticsService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/get-all-specialities")
    public ResponseEntity<List<String>> getAllSpecialities() {
        List<String> specialities = Arrays.stream(Speciality.values())
                .map(Speciality::toString)
                .collect(Collectors.toList());
        return ResponseEntity.ok(specialities);
    }

    @GetMapping("/get-notification-services")
    public ResponseEntity<List<NotificationService>> getNotificationServices() {
        List<NotificationService> services = staticsService.getNotificationServices();
        return ResponseEntity.ok(services);
    }
}
