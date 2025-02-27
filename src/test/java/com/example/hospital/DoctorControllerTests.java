package com.example.hospital;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hospital.models.Appointment;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.Patient;
import com.example.hospital.models.enums.AppointmentStatus;
import com.example.hospital.services.state.CompletedState;

import jakarta.transaction.Transactional;

import com.example.hospital.common.ReusableData;
import com.example.hospital.dal.AppointmentDAL;
import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.dal.PatientDAL;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@WithMockUser(username = "doctor", roles = { "DOCTOR" })
public class DoctorControllerTests {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private AppointmentDAL appointmentDAL;

        @Autowired
        private DoctorDAL doctorDAL;

        @Autowired
        private PatientDAL patientDAL;

        private Doctor doctor = ReusableData.createDoctor();
        private Patient patient = ReusableData.createPatient();
        
        private Appointment appointment = new Appointment(
                        LocalDate.now().plusDays(1),
                        LocalTime.of(10, 0),
                        doctor,
                        patient);

        @Test
        void testCompleteAppointment() throws Exception {
                doctor = doctorDAL.save(doctor);
                patient = patientDAL.save(patient);
                appointment = appointmentDAL.save(appointment);

                mockMvc.perform(post("/api/doctors/appointments/{appointmentId}/confirm", appointment.getId()))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.status").value(AppointmentStatus.CONFIRMED.toString()));

                mockMvc.perform(post("/api/doctors/appointments/{appointmentId}/complete", appointment.getId()))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.status").value(AppointmentStatus.COMPLETED.toString()));
        }

        @Test
        public void testDeclineAppointment() throws Exception {
                doctorDAL.save(doctor);
                patientDAL.save(patient);

                appointmentDAL.save(appointment);

                mockMvc.perform(post("/api/doctors/decline-appointment/{appointmentId}", appointment.getId()))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.content()
                                                .string(ResponseMessages.APPOINTMENT_DECLINED));
        }

        @Test
        public void testDeclinewithStatusOtherThanRequested() throws Exception {
                doctorDAL.save(doctor);
                patientDAL.save(patient);

                appointment.setStatus(AppointmentStatus.COMPLETED);
                appointment.setState(new CompletedState());
                appointmentDAL.save(appointment);

                mockMvc.perform(post("/api/doctors/decline-appointment/{appointmentId}", appointment.getId()))
                                .andExpect(status().isBadRequest());
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
}
