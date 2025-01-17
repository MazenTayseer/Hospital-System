package com.example.hospital;

import com.example.hospital.dal.UserDAL;
import com.example.hospital.common.ReusableData;
import com.example.hospital.dto.UserDto;
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

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

        @Autowired
        private UserDAL userDAL;

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
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.roles[?(@.name == 'DOCTOR')]").exists())
                                .andExpect(jsonPath("$.data.notificationServices[?(@.name == 'EMAIL')]").exists())
                                .andExpect(jsonPath("$.data.speciality").value(Speciality.SURGEON.toString()));
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
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.notificationServices[?(@.name == 'EMAIL')]").exists())
                                .andExpect(jsonPath("$.data.roles[?(@.name == 'NURSE')]").exists());
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
                                .andExpect(jsonPath("$.success").value(true))
                                .andExpect(jsonPath("$.data.notificationServices[?(@.name == 'EMAIL')]").exists())
                                .andExpect(jsonPath("$.data.roles[?(@.name == 'PATIENT')]").exists());
        }

        void testCreateVolunteer() throws Exception {
                Volunteer volunteer = new Volunteer(
                                "VolunteerFirstName",
                                "VolunteerLastName",
                                "volunteer@example.com",
                                "password123",
                                "+201000000002",
                                25,
                                Gender.MALE);

                UserDto<Volunteer> request = new UserDto<>();
                request.setUser(volunteer);
                request.setNotificationServiceIds(Collections.emptyList());

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
                Doctor doctor = ReusableData.createDoctor();
                User savedUser = userDAL.save(doctor);

                mockMvc.perform(delete("/api/managers/delete/{userId}", savedUser.getId()))
                                .andExpect(status().isNoContent());
        }
        
        @Test
        void testUpdateDoctor() throws Exception {
            
            Doctor doctor = ReusableData.createDoctor();
            User savedUser = userDAL.save(doctor); 
        
            Doctor updatedDoctor = (Doctor) savedUser; 
            updatedDoctor.setFirstName("UpdatedFirstName");
            updatedDoctor.setLastName("UpdatedLastName");
        
            UserDto<Doctor> request = new UserDto<>();
            request.setUser(updatedDoctor);
            request.setNotificationServiceIds(Collections.emptyList()); // Use real service IDs if available
        
            mockMvc.perform(put("/api/managers/update/{userId}", savedUser.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstName").value("UpdatedFirstName"))
                    .andExpect(jsonPath("$.lastName").value("UpdatedLastName"));
        }
        

}