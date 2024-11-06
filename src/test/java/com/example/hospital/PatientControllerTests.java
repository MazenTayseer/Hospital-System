package com.example.hospital;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hospital.models.Appointment;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.Patient;
import com.example.hospital.models.Review;
import com.example.hospital.models.enums.AppointmentStatus;
import com.example.hospital.models.enums.Speciality;
import com.example.hospital.repositories.AppointmentRepository;
import com.example.hospital.repositories.DoctorRepository;
import com.example.hospital.repositories.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTests {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private DoctorRepository doctorRepository;

        @Autowired
        private PatientRepository patientRepository;

        @Autowired
        private AppointmentRepository appointmentRepository;

        private Doctor doctor;
        private Patient patient;
        private Appointment appointment;

        @BeforeEach
        void setup() {
                doctor = new Doctor(
                                "Doctor",
                                "Doctor",
                                "Doctor@Doctor.com",
                                "password",
                                "+201000000000",
                                21,
                                Speciality.SURGEON);

                patient = new Patient(
                                "Patient",
                                "Patient",
                                "Patient@Patient.com",
                                "password",
                                "+201000000001",
                                21);

                appointment = new Appointment(
                                LocalDate.now().plusDays(1),
                                LocalTime.now().plusHours(1).withMinute(0),
                                doctor,
                                patient);
        }

        @Test
        public void testBookAppointment() throws Exception {
                doctorRepository.save(doctor);
                patientRepository.save(patient);

                mockMvc.perform(post("/api/patients/book-appointment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(appointment)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").exists())
                                .andExpect(jsonPath("$.doctor.id").value(doctor.getId()))
                                .andExpect(jsonPath("$.patient.id").value(patient.getId()));
        }

        @Test
        public void testCancelAppointment() throws Exception {
                doctorRepository.save(doctor);
                patientRepository.save(patient);

                appointmentRepository.save(appointment);

                mockMvc.perform(post("/api/patients/cancel-appointment/{appointmentId}", appointment.getId()))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.content()
                                                .string(ResponseMessages.APPOINTMENT_CANCELLED));
        }

        @Test
        public void testCancelCompletedAppointment() throws Exception {
                doctorRepository.save(doctor);
                patientRepository.save(patient);

                appointment.setStatus(AppointmentStatus.COMPLETED);
                appointmentRepository.save(appointment);

                mockMvc.perform(post("/api/patients/cancel-appointment/{appointmentId}", appointment.getId()))
                                .andExpect(status().isBadRequest())
                                .andExpect(MockMvcResultMatchers.content()
                                                .string(ResponseMessages.CANNOT_CANCEL_COMPLETED_APPOINTMENT));
        }

        @Test
        public void testGetAppointmentById() throws Exception {
                doctorRepository.save(doctor);
                patientRepository.save(patient);
                appointmentRepository.save(appointment);

                mockMvc.perform(get("/api/patients/appointments/{appointmentId}", appointment.getId()))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(appointment.getId()))
                                .andExpect(jsonPath("$.doctor.id").value(doctor.getId()))
                                .andExpect(jsonPath("$.patient.id").value(patient.getId()));
        }

        @Test
        public void testGetAppointmentsByPatientId() throws Exception {
                doctorRepository.save(doctor);
                patientRepository.save(patient);
                appointmentRepository.save(appointment);

                mockMvc.perform(get("/api/patients/{patientId}/appointments", patient.getId()))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].id").value(appointment.getId()))
                                .andExpect(jsonPath("$[0].doctor.id").value(doctor.getId()))
                                .andExpect(jsonPath("$[0].patient.id").value(patient.getId()));
        }

        @Test
        public void testReviewDoctor() throws Exception {
                doctorRepository.save(doctor);
                patientRepository.save(patient);
                appointmentRepository.save(appointment);

                Review review = new Review(
                        5D, 
                        "Great doctor", 
                        doctor, 
                        patient
                );

                mockMvc.perform(post("/api/patients/review-doctor")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(review)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").exists())
                                .andExpect(jsonPath("$.rating").value(5))
                                .andExpect(jsonPath("$.comment").value("Great doctor"))
                                .andExpect(jsonPath("$.doctor.id").value(doctor.getId()));
        }

        @Test
        public void testReviewDoctorFailed() throws Exception {
                doctorRepository.save(doctor);
                patientRepository.save(patient);

                Review review = new Review(
                        5D, 
                        "Great doctor", 
                        doctor, 
                        patient
                );

                mockMvc.perform(post("/api/patients/review-doctor")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(review)))
                                .andExpect(status().isBadRequest())
                                .andExpect(MockMvcResultMatchers.content()
                                                .string(ResponseMessages.CANNOT_REVIEW_DOCTOR));
        }

}
