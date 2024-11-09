// package com.example.hospital;

// import com.example.hospital.dal.VolunteerDAL;
// import com.example.hospital.models.Volunteer;
// import com.example.hospital.models.enums.Gender;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;

// import jakarta.transaction.Transactional;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest
// @AutoConfigureMockMvc
// @Transactional
// @WithMockUser(username = "manager", roles = {"MANAGER"})
// public class VolunteerControllerTests {

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @Autowired
//     private VolunteerDAL volunteerDAL;

//     private Volunteer volunteer = new Volunteer(
//             "VolunteerFirstName",
//             "VolunteerLastName",
//             "volunteer@example.com",
//             "password123",
//             "+201000000002",
//             25,
//             Gender.MALE,
//             "First Aid, Communication",
//             "Weekends"
//     );

//     @Test
//     public void testCreateVolunteer() throws Exception {
//         mockMvc.perform(post("/api/managers/create-volunteer")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(volunteer)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").exists())
//                 .andExpect(jsonPath("$.firstName").value("VolunteerFirstName"))
//                 .andExpect(jsonPath("$.skills").value("First Aid, Communication"))
//                 .andExpect(jsonPath("$.availability").value("Weekends"));
//     }

//     @Test
//     public void testGetVolunteerById() throws Exception {
//         Volunteer savedVolunteer = volunteerDAL.save(volunteer);

//         mockMvc.perform(get("/volunteers/{id}", savedVolunteer.getId()))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(savedVolunteer.getId()))
//                 .andExpect(jsonPath("$.firstName").value("VolunteerFirstName"))
//                 .andExpect(jsonPath("$.skills").value("First Aid, Communication"))
//                 .andExpect(jsonPath("$.availability").value("Weekends"));
//     }

//     @Test
//     public void testGetAllVolunteers() throws Exception {
//         volunteerDAL.save(volunteer);

//         mockMvc.perform(get("/volunteers"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(volunteerDAL.findAll().size()));
//     }

//     @Test
//     public void testDeleteVolunteer() throws Exception {
//         Volunteer savedVolunteer = volunteerDAL.save(volunteer);

//         mockMvc.perform(delete("/volunteers/{id}", savedVolunteer.getId()))
//                 .andExpect(status().isNoContent());
//     }
// }
