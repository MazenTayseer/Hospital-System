package com.example.hospital.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.ResponseMessages;
import com.example.hospital.models.Appointment;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.Review;
import com.example.hospital.services.PatientService;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/book-appointment")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {
        Appointment bookedAppointment = patientService.bookAppointment(appointment);
        return ResponseEntity.ok(bookedAppointment);
    }    

    @PostMapping("/cancel-appointment/{appointmentId}")
    public ResponseEntity<String> bookAppointment(@PathVariable Long appointmentId) {
        patientService.cancelAppointment(appointmentId);
        return ResponseEntity.ok(ResponseMessages.APPOINTMENT_CANCELLED);
    }  
    
    @PostMapping("/review-doctor")
    public ResponseEntity<Review> reviewDoctor(@RequestBody Review review) {
        Review createdReview = patientService.reviewDoctor(review);
        return ResponseEntity.ok(createdReview);
    }
    

    @GetMapping("/appointments")
    public ResponseEntity<?> getAppointments(@RequestParam(required = false) Long appointmentId) {
        if (appointmentId != null) {
            Appointment appointment = (Appointment) patientService.getAppointments(appointmentId);
            return ResponseEntity.ok(appointment);
        } else {
            List<Appointment> appointments = patientService.getAllAppointments();
            return ResponseEntity.ok(appointments);
        }
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> viewDoctors(@RequestParam(required = false) String speciality) {
        List<Doctor> doctors = patientService.viewDoctors(speciality);
        return ResponseEntity.ok(doctors);
    }
    
}
