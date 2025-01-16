package com.example.hospital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.dal.AppointmentDAL;
import com.example.hospital.models.Appointment;
import com.example.hospital.models.Doctor;
import com.example.hospital.repositories.DoctorRepository;

@Service
public class DoctorService {
  private AppointmentDAL appointmentDAL;

  @Autowired
  private DoctorRepository doctorRepository;

    public DoctorService(AppointmentDAL appointmentDAL) {
        this.appointmentDAL = appointmentDAL;
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
    Optional<Doctor> doctor = doctorRepository.findById(doctorId);
    return doctor.orElse(null);
}

}
