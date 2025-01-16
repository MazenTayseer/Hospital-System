package com.example.hospital.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.ResponseMessages;
import com.example.hospital.ResponseWrapper;
import com.example.hospital.dal.AppointmentDAL;
import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.dal.PatientDAL;
import com.example.hospital.dal.ReviewDAL;
import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.models.Appointment;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.Patient;
import com.example.hospital.models.Review;
import com.example.hospital.services.DoctorService;
import com.example.hospital.services.PatientService;
import com.example.hospital.services.singleton.PatientServiceSingleton;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;
    private final DoctorService doctorService;

    public PatientController(
            DoctorDAL doctorDAL,
            PatientDAL patientDAL,
            AppointmentDAL appointmentDAL,
            ReviewDAL reviewDAL,
            DoctorService doctorService
    ) {
        this.patientService = PatientServiceSingleton.getInstance(
            doctorDAL, 
            patientDAL, 
            appointmentDAL, 
            reviewDAL
        );
        this.doctorService = doctorService;
    }

    @PostMapping("/book-appointment")
    public ResponseEntity<ResponseWrapper<Appointment>> bookAppointment(@RequestBody AppointmentDto request) {
        try {
            Doctor doctor = doctorService.getDoctorById(request.getDoctorId());
            Patient patient = patientService.getPatientById(request.getPatientId());
    
            Appointment appointment = new Appointment(
                LocalDate.parse(request.getDate()),
                LocalTime.parse(request.getTimeFrom()),
                doctor,
                patient
            );
    
            Appointment bookedAppointment = patientService.registerAppointment(appointment);
            return ResponseEntity.ok(new ResponseWrapper<>(bookedAppointment));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseWrapper<>("Error creating appointment: " + e.getMessage()));
        }
    }

    @PostMapping("/cancel-appointment/{appointmentId}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long appointmentId) {
        patientService.removeAppointment(appointmentId);
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

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
