package com.example.hospital;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hospital.common.ReusableData;
import com.example.hospital.dal.AppointmentDAL;
import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.dal.PatientDAL;
import com.example.hospital.models.Appointment;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.Patient;
import com.example.hospital.models.Review;
import com.example.hospital.models.enums.AppointmentStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(username = "patient", roles = {"PATIENT"})
public class PatientControllerTests {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private DoctorDAL doctorDAL;

        @Autowired
        private PatientDAL patientDAL;

        @Autowired
        private AppointmentDAL appointmentDAL;


        private Doctor doctor = ReusableData.createDoctor();
        private Patient patient = ReusableData.createPatient();
        
        private Appointment appointment = new Appointment(
                        LocalDate.now().plusDays(1),
                        LocalTime.of(10, 0),
                        doctor,
                        patient);

        @Test
        public void testBookAppointment() throws Exception {
                doctorDAL.save(doctor);
                patientDAL.save(patient);

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
                doctorDAL.save(doctor);
                patientDAL.save(patient);

                appointmentDAL.save(appointment);

                mockMvc.perform(post("/api/patients/cancel-appointment/{appointmentId}", appointment.getId()))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.content()
                                                .string(ResponseMessages.APPOINTMENT_CANCELLED));
        }

        @Test
        public void testCancelCompletedAppointment() throws Exception {
                doctorDAL.save(doctor);
                patientDAL.save(patient);

                appointment.setStatus(AppointmentStatus.COMPLETED);
                appointmentDAL.save(appointment);

                mockMvc.perform(post("/api/patients/cancel-appointment/{appointmentId}", appointment.getId()))
                                .andExpect(status().isBadRequest())
                                .andExpect(MockMvcResultMatchers.content()
                                                .string(ResponseMessages.CANNOT_CANCEL_COMPLETED_APPOINTMENT));
        }

        @Test
        public void testGetAppointmentById() throws Exception {
            doctorDAL.save(doctor);
            patientDAL.save(patient);
            appointmentDAL.save(appointment);
        
            mockMvc.perform(get("/api/patients/appointments")
                            .param("appointmentId", String.valueOf(appointment.getId())))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(appointment.getId()))
                    .andExpect(jsonPath("$.doctor.id").value(doctor.getId()))
                    .andExpect(jsonPath("$.patient.id").value(patient.getId()));
        }
        
        @Test
        public void testGetAllAppointments() throws Exception {
            doctorDAL.save(doctor);
            patientDAL.save(patient);
            appointmentDAL.save(appointment);
        
            mockMvc.perform(get("/api/patients/appointments"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(appointmentDAL.count()));
        }

        @Test
        public void testReviewDoctor() throws Exception {
                doctorDAL.save(doctor);
                patientDAL.save(patient);
                appointmentDAL.save(appointment);

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
                doctorDAL.save(doctor);
                patientDAL.save(patient);

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

        @Test
        public void testViewDoctors() throws Exception {
                doctorDAL.save(doctor);

                mockMvc.perform(get("/api/patients/doctors")
                                .param("param", "surgeon"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.length()").value(doctorDAL.count()));
        }
}
