package com.example.hospital.services.users;

import org.springframework.stereotype.Service;

import com.example.hospital.dal.AppointmentDAL;
import com.example.hospital.models.Appointment;
import com.example.hospital.models.enums.AppointmentStatus;
@Service
public class DoctorService {
    private AppointmentDAL appointmentDAL;

    public DoctorService(AppointmentDAL appointmentDAL) {
        this.appointmentDAL = appointmentDAL;
    }

    public Appointment completeAppointment(Long appointmentId) {
        Appointment appointment = appointmentDAL.findById(appointmentId);
        appointment.setStatus(AppointmentStatus.COMPLETED);
        return appointmentDAL.save(appointment);
    }
}
