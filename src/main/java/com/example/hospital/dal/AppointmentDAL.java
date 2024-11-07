package com.example.hospital.dal;

import com.example.hospital.ResponseMessages;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Appointment;

import org.springframework.stereotype.Component;
import com.example.hospital.repositories.AppointmentRepository;

import java.util.List;

@Component
public class AppointmentDAL {

    private final AppointmentRepository appointmentRepository;

    public AppointmentDAL(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ResponseMessages.record_not_found("Appointment"))
        );
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void delete(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

    public long count() {
        return appointmentRepository.count();
    }

    public void deleteAll() {
        appointmentRepository.deleteAll();
    }

    public boolean existsByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        return appointmentRepository.existsByPatientIdAndDoctorId(patientId, doctorId);
    }
}
