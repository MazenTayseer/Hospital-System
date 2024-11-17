package com.example.hospital;

import com.example.hospital.common.ReusableData;
import com.example.hospital.dto.UserDto;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
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

import java.util.Collections;

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
        Doctor doctor = ReusableData.createDoctor();

        UserDto<Doctor> request = new UserDto<>();
        request.setUser(doctor);
        request.setNotificationServiceIds(Collections.emptyList());

        mockMvc.perform(post("/api/managers/create-doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roles[?(@.name == 'DOCTOR')]").exists())
                .andExpect(jsonPath("$.notificationServices[?(@.name == 'EMAIL')]").exists())
                .andExpect(jsonPath("$.speciality").value(Speciality.SURGEON.toString()));
    }

    @Test
    void testCreateNurse() throws Exception {
        Nurse nurse = ReusableData.createNurse();

        UserDto<Nurse> request = new UserDto<>();
        request.setUser(nurse);
        request.setNotificationServiceIds(Collections.emptyList());

        mockMvc.perform(post("/api/managers/create-nurse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.notificationServices[?(@.name == 'EMAIL')]").exists())
                .andExpect(jsonPath("$.roles[?(@.name == 'NURSE')]").exists());
    }

    @Test
    void testCreatePatient() throws Exception {
        Patient patient = ReusableData.createPatient();

        UserDto<Patient> request = new UserDto<>();
        request.setUser(patient);
        request.setNotificationServiceIds(Collections.emptyList());

        mockMvc.perform(post("/api/managers/create-patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.notificationServices[?(@.name == 'EMAIL')]").exists())
                .andExpect(jsonPath("$.roles[?(@.name == 'PATIENT')]").exists());
    }
}
