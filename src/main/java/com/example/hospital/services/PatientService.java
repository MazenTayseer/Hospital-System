package com.example.hospital.services;

import com.example.hospital.ResponseMessages;
import com.example.hospital.dal.AppointmentDAL;
import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.dal.PatientDAL;
import com.example.hospital.dal.ReviewDAL;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.*;
import com.example.hospital.models.enums.Speciality;
import com.example.hospital.services.command.BookAppointmentCommand;
import com.example.hospital.services.command.CancelAppointmentCommand;
import com.example.hospital.services.command.CommandInvoker;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class PatientService {
    private final CommandInvoker commandInvoker = new CommandInvoker();
    private DoctorDAL doctorDAL;
    private PatientDAL patientDAL;
    private AppointmentDAL appointmentDAL;
    private ReviewDAL reviewDAL;

    public PatientService(
            DoctorDAL doctorDAL,
            PatientDAL patientDAL,
            AppointmentDAL appointmentDAL,
            ReviewDAL reviewDAL) {
        this.doctorDAL = doctorDAL;
        this.patientDAL = patientDAL;
        this.appointmentDAL = appointmentDAL;
        this.reviewDAL = reviewDAL;
    }

    @Transactional
    public Appointment registerAppointment(Appointment appointment) {
        BookAppointmentCommand command = new BookAppointmentCommand(this, appointment);
        commandInvoker.executeCommand(command);
        return appointment;
    }
    
    public Appointment bookAppointment(Appointment appointment) {
      Doctor doctor = doctorDAL.findById(appointment.getDoctor().getId());
      Patient patient = patientDAL.findById(appointment.getPatient().getId());

      appointment.setDoctor(doctor);
      appointment.setPatient(patient);

      return appointmentDAL.save(appointment);
    }

    public Patient getPatientById(Long patientId) {
        Patient patient = patientDAL.findById(patientId);
        return patient;
    }

    public void removeAppointment(Long appointmentId) {
        CancelAppointmentCommand command = new CancelAppointmentCommand(this, appointmentId);
        commandInvoker.executeCommand(command);
    }

    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentDAL.findById(appointmentId);
        appointment.cancel();
        appointmentDAL.save(appointment);
    }

    public Appointment getAppointments(Long appointmentId) {
        return appointmentDAL.findById(appointmentId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAL.findAll();
    }

    public Review reviewDoctor(Review review) {
        boolean canReview = appointmentDAL.existsByPatientIdAndDoctorId(review.getPatient().getId(),
                review.getDoctor().getId());
        if (!canReview) {
            throw new BadRequestException(ResponseMessages.CANNOT_REVIEW_DOCTOR);
        }

        return reviewDAL.save(review);
    }

    public List<Doctor> viewDoctors(String speciality) {
        if (speciality != null && !speciality.isEmpty()) {
            Speciality formattedSpeciality = Speciality.valueOf(speciality.toUpperCase());
            return doctorDAL.findDoctorsBySpeciality(formattedSpeciality);
        } else {
            return doctorDAL.findAllDoctors();
        }
    }

    public List<Appointment> getAppointmentsForPatient(Long patientId) {
        return appointmentDAL.findAppointmentsByPatientId(patientId);
    }
    

}
