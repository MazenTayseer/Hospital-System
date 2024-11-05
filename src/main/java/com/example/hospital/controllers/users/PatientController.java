package com.example.hospital.controllers.users;

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
import com.example.hospital.services.users.PatientService;


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
    

    @GetMapping("/appointments/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long appointmentId) {
        Appointment appointment = patientService.getAppointmentById(appointmentId);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        List<Appointment> appointments = patientService.getAppointmentsByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }
    
}
