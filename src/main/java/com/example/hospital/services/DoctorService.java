package com.example.hospital.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospital.dal.AppointmentDAL;
import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.models.Appointment;
import com.example.hospital.models.Doctor;
@Service
public class DoctorService {
    private AppointmentDAL appointmentDAL;
    private DoctorDAL doctorDAL;

    public DoctorService(AppointmentDAL appointmentDAL, DoctorDAL doctorDAL) {
        this.appointmentDAL = appointmentDAL;
        this.doctorDAL = doctorDAL;
    }

    public Appointment changeAppointmentStatus(Long appointmentId) {
        Appointment appointment = appointmentDAL.findById(appointmentId);
        appointment.nextState();
        return appointmentDAL.save(appointment);
    }

    public void declineAppointment(Long appointmentId) {
        Appointment appointment = appointmentDAL.findById(appointmentId);
        appointment.decline();
        appointmentDAL.save(appointment);
    }

    public Appointment getAppointments(Long appointmentId) {
        return appointmentDAL.findById(appointmentId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAL.findAll();
    }

    public Doctor getDoctorById(Long doctorId) {
        return doctorDAL.findById(doctorId);
    }
}
