package com.example.hospital;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
import com.example.hospital.models.Volunteer;
import com.example.hospital.models.User;
import com.example.hospital.models.enums.Gender;
import com.example.hospital.models.enums.Speciality;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@WithMockUser(username = "manager", roles = { "MANAGER" })
class ManagerControllerTests {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void testCreateDoctor() throws Exception {
                Doctor doctor = new Doctor(
                                "doctor",
                                "doctor",
                                "doctor@eng.asu.edu.eg",
                                "password",
                                "+201279936001",
                                21,
                                Gender.MALE,
                                Speciality.SURGEON);

                mockMvc.perform(post("/api/managers/create-doctor")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(doctor)))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.roles[?(@.name == 'DOCTOR')]").exists())
                                .andExpect(jsonPath("$.speciality").value(Speciality.SURGEON.toString()));
        }

        @Test
        void testCreateNurse() throws Exception {
                Nurse nurse = new Nurse(
                                "nurse",
                                "nurse",
                                "nurse@eng.asu.edu.eg",
                                "password",
                                "+201279936002",
                                21,
                                Gender.MALE);

                mockMvc.perform(post("/api/managers/create-nurse")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(nurse)))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.roles[?(@.name == 'NURSE')]").exists());
        }

        @Test
        void testCreatePatient() throws Exception {
                Patient patient = new Patient(
                                "patient",
                                "patient",
                                "patient@eng.asu.edu.eg",
                                "password",
                                "+201279936003",
                                21,
                                Gender.MALE);

                mockMvc.perform(post("/api/managers/create-patient")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(patient)))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.roles[?(@.name == 'PATIENT')]").exists());
        }

        @Test
        void testCreateVolunteer() throws Exception {
                Volunteer volunteer = new Volunteer(
                                "VolunteerFirstName",
                                "VolunteerLastName",
                                "volunteer@example.com",
                                "password123",
                                "+201000000002",
                                25,
                                Gender.MALE,
                                "First Aid, Communication",
                                "Weekends");

                mockMvc.perform(post("/api/managers/create-volunteer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(volunteer)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").exists())
                                .andExpect(jsonPath("$.firstName").value("VolunteerFirstName"))
                                .andExpect(jsonPath("$.skills").value("First Aid, Communication"))
                                .andExpect(jsonPath("$.availability").value("Weekends"))
                                .andExpect(jsonPath("$.roles[?(@.name == 'VOLUNTEER')]").exists());
        }
        @Test
        public void testDeleteUser() throws Exception {
            User savedUser = userDAL.save(user);
    
            mockMvc.perform(delete("/api/volunteers/{id}", savedUser.getId()))
                    .andExpect(status().isNoContent());
        }
}
