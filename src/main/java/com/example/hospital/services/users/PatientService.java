package com.example.hospital.services.users;

import com.example.hospital.ResponseMessages;
import com.example.hospital.models.*;
import com.example.hospital.repositories.*;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


@Service
public class PatientService {
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;
    private ReviewRepository reviewRepository;

    public PatientService(
        DoctorRepository doctorRepository, 
        PatientRepository patientRepository, 
        AppointmentRepository appointmentRepository,
        ReviewRepository reviewRepository
    ) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Appointment bookAppointment(Appointment appointment) {
        Doctor doctor = doctorRepository.findById(appointment.getDoctor().getId()).orElseThrow(
            () -> new IllegalArgumentException(ResponseMessages.record_not_found("Doctor"))
        );
        Patient patient = patientRepository.findById(appointment.getPatient().getId()).orElseThrow(
            () -> new IllegalArgumentException(ResponseMessages.record_not_found("Patient"))
        );

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        
        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
            () -> new IllegalArgumentException(ResponseMessages.record_not_found("Appointment"))
        );
        appointmentRepository.delete(appointment);
    }

    public Appointment getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(
            () -> new IllegalArgumentException(ResponseMessages.record_not_found("Appointment"))
        );
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public Review reviewDoctor(Review review) {
        boolean canReview = canPatientReviewDoctor(review.getPatient().getId(), review.getDoctor().getId());
        if (!canReview) {
            throw new IllegalArgumentException(ResponseMessages.CANNOT_REVIEW_DOCTOR);
        }

        return reviewRepository.save(review);
    }

    private boolean canPatientReviewDoctor(Long patientId, Long doctorId) {
        return appointmentRepository.existsByPatientIdAndDoctorId(patientId, doctorId);
    }
}
