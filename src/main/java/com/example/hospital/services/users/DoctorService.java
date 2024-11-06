package com.example.hospital.services.users;

import org.springframework.stereotype.Service;

import com.example.hospital.ResponseMessages;
import com.example.hospital.models.Appointment;
import com.example.hospital.models.enums.AppointmentStatus;
import com.example.hospital.repositories.AppointmentRepository;

@Service
public class DoctorService {
    private AppointmentRepository appointmentRepository;

    public DoctorService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment completeAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
            () -> new IllegalArgumentException(ResponseMessages.record_not_found("Appointment"))
        );
        appointment.setStatus(AppointmentStatus.COMPLETED);
        return appointmentRepository.save(appointment);
    }
}
