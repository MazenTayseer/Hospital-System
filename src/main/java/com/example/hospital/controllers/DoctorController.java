package com.example.hospital.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.ResponseMessages;
import com.example.hospital.models.Appointment;
import com.example.hospital.services.DoctorService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @PostMapping("/appointments/{appointmentId}/confirm")
    public ResponseEntity<Appointment> confirmAppointment(@PathVariable Long appointmentId) {
        Appointment updatedAppointment = doctorService.changeAppointmentState(appointmentId);
        return ResponseEntity.ok(updatedAppointment);
    }

    @PostMapping("/appointments/{appointmentId}/complete")
    public ResponseEntity<Appointment> completeAppointment(@PathVariable Long appointmentId) {
        Appointment updatedAppointment = doctorService.changeAppointmentState(appointmentId);
        return ResponseEntity.ok(updatedAppointment);
    }    

    @PostMapping("/decline-appointment/{appointmentId}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long appointmentId) {
        doctorService.rejectAppointment(appointmentId);
        return ResponseEntity.ok(ResponseMessages.APPOINTMENT_DECLINED);
    }

    @GetMapping("/appointments")
    public ResponseEntity<?> getAppointments(@RequestParam(required = false) Long appointmentId) {
        if (appointmentId != null) {
            Appointment appointment = (Appointment) doctorService.getAppointments(appointmentId);
            return ResponseEntity.ok(appointment);
        } else {
            List<Appointment> appointments = doctorService.getAllAppointments();
            return ResponseEntity.ok(appointments);
        }
    }
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointment>> getDoctorAppointments(@PathVariable("id") Long doctorId) {
        List<Appointment> appointments = doctorService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }
    



}
