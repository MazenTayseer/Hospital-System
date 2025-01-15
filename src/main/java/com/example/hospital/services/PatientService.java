package com.example.hospital.services;

import com.example.hospital.ResponseMessages;
import com.example.hospital.dal.AppointmentDAL;
import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.dal.PatientDAL;
import com.example.hospital.dal.ReviewDAL;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.*;
import com.example.hospital.models.enums.Speciality;
import com.example.hospital.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private DoctorDAL doctorDAL;
    private PatientDAL patientDAL;
    private AppointmentDAL appointmentDAL;
    private ReviewDAL reviewDAL;

    @Autowired
    private PatientRepository patientRepository;

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
    public Appointment bookAppointment(Appointment appointment) {
      Doctor doctor = doctorDAL.findById(appointment.getDoctor().getId());
      Patient patient = patientDAL.findById(appointment.getPatient().getId());

      appointment.setDoctor(doctor);
      appointment.setPatient(patient);

      return appointmentDAL.save(appointment);
    }

   public Patient getPatientById(Long patientId) {
    Optional<Patient> patient = patientRepository.findById(patientId);
    return patient.orElse(null); // Return null if patient not found
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

}
