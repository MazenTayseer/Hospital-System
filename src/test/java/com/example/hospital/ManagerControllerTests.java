package com.example.hospital;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;

import com.example.hospital.models.enums.Role;
import com.example.hospital.models.enums.Speciality;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ManagerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateDoctor() throws Exception {
        Doctor doctor = new Doctor(
                "Mazen",
                "Tayseer",
                "20P7460@eng.asu.edu.eg",
                "password",
                "+201279936009",
                21,
                Speciality.SURGEON);

        mockMvc.perform(post("/api/managers/create-doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.role").value(Role.DOCTOR.toString()))
                .andExpect(jsonPath("$.speciality").value(Speciality.SURGEON.toString()));
    }

    @Test
    void testCreateNurse() throws Exception {
        Nurse nurse = new Nurse(
                "Mazen",
                "Tayseer",
                "20P7460@eng.asu.edu.eg",
                "password",
                "+201279936009",
                21);

        mockMvc.perform(post("/api/managers/create-nurse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nurse)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.role").value(Role.NURSE.toString()));
    }

    @Test
    void testCreatePatient() throws Exception {
        Patient patient = new Patient(
                "Mazen",
                "Tayseer",
                "20P7460@eng.asu.edu.eg",
                "password",
                "+201279936009",
                21);

        mockMvc.perform(post("/api/managers/create-nurse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.role").value(Role.PATIENT.toString()));
    }
}
