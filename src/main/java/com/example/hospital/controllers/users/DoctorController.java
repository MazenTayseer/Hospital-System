package com.example.hospital.controllers.users;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.models.Appointment;
import com.example.hospital.services.users.DoctorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/appointments/{appointmentId}/complete")
    public ResponseEntity<Appointment> completeAppointment(@PathVariable Long appointmentId) {
        Appointment updatedAppointment = doctorService.completeAppointment(appointmentId);
        return ResponseEntity.ok(updatedAppointment);
    }
    
}
