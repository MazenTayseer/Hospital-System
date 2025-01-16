package com.example.hospital;

import com.example.hospital.dal.PatientDAL;
import com.example.hospital.models.Patient;
import com.example.hospital.models.MedicationTreatment;
import com.example.hospital.models.SurgeryTreatment;
import com.example.hospital.models.TherapyTreatment;
import com.example.hospital.models.enums.Gender;
import com.example.hospital.repositories.MedicationTreatmentRepository;
import com.example.hospital.repositories.SurgeryTreatmentRepository;
import com.example.hospital.repositories.TherapyTreatmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class PatientTreatmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientDAL patientDAL;

    @Autowired
    private MedicationTreatmentRepository medicationTreatmentRepository;

    @Autowired
    private SurgeryTreatmentRepository surgeryTreatmentRepository;

    @Autowired
    private TherapyTreatmentRepository therapyTreatmentRepository;

    @Test
    public void testMedicationTreatmentReport() throws Exception {
        // Create and save a patient
        Patient patient = new Patient(
            "John",
            "Doe",
            "john.doe@example.com",
            "password123",
            "+201000000003",
            30,
            Gender.MALE
        );
        Patient savedPatient = patientDAL.save(patient);

        // Create and save a medication treatment
        MedicationTreatment medicationTreatment = new MedicationTreatment();
        medicationTreatment.setPatient(savedPatient);
        medicationTreatment.setMedicationName("Ibuprofen");
        medicationTreatment.setDosage("200mg");
        medicationTreatment.setDuration(7);
        medicationTreatment.setFrequency("Twice a day");
        MedicationTreatment savedTreatment = medicationTreatmentRepository.save(medicationTreatment);

        // Perform GET request and validate response
        mockMvc.perform(get("/api/treatment/report/medication/{treatmentId}", savedTreatment.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Treatment Report"))
                .andExpect(jsonPath("$.hospital").value("Ain Shams Charity Hospital"))
                .andExpect(jsonPath("$.patientId").value(savedPatient.getId()))
                .andExpect(jsonPath("$.medicationName").value("Ibuprofen"))
                .andExpect(jsonPath("$.dosage").value("200mg"))
                .andExpect(jsonPath("$.duration").value("7 days"))
                .andExpect(jsonPath("$.frequency").value("Twice a day"))
                .andExpect(jsonPath("$.footer").value("Report Generated Successfully!"));
    }

    @Test
    public void testSurgeryTreatmentReport() throws Exception {
        // Create and save a patient
        Patient patient = new Patient(
            "Jane",
            "Smith",
            "jane.smith@example.com",
            "password456",
            "+201000000004",
            40,
            Gender.FEMALE
        );
        Patient savedPatient = patientDAL.save(patient);

        // Create and save a surgery treatment
        SurgeryTreatment surgeryTreatment = new SurgeryTreatment();
        surgeryTreatment.setPatient(savedPatient);
        surgeryTreatment.setSurgeryType("Appendectomy");
        surgeryTreatment.setLocation("Operating Room 1");
        surgeryTreatment.setSurgeon("Dr. Brown");
        surgeryTreatment.setDate("2025-01-20");
        SurgeryTreatment savedTreatment = surgeryTreatmentRepository.save(surgeryTreatment);

        // Perform GET request and validate response
        mockMvc.perform(get("/api/treatment/report/surgery/{treatmentId}", savedTreatment.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Treatment Report"))
                .andExpect(jsonPath("$.hospital").value("Ain Shams Charity Hospital"))
                .andExpect(jsonPath("$.patientId").value(savedPatient.getId()))
                .andExpect(jsonPath("$.surgeryType").value("Appendectomy"))
                .andExpect(jsonPath("$.location").value("Operating Room 1"))
                .andExpect(jsonPath("$.surgeon").value("Dr. Brown"))
                .andExpect(jsonPath("$.date").value("2025-01-20"))
                .andExpect(jsonPath("$.footer").value("Report Generated Successfully!"));
    }

    @Test
    public void testTherapyTreatmentReport() throws Exception {
        // Create and save a patient
        Patient patient = new Patient(
            "Emily",
            "Davis",
            "emily.davis@example.com",
            "password789",
            "+201000000005",
            25,
            Gender.FEMALE
        );
        Patient savedPatient = patientDAL.save(patient);

        // Create and save a therapy treatment
        TherapyTreatment therapyTreatment = new TherapyTreatment();
        therapyTreatment.setPatient(savedPatient);
        therapyTreatment.setTherapyType("Physical Therapy");
        therapyTreatment.setDuration(14);
        therapyTreatment.setFrequency("Daily");
        therapyTreatment.setNotes("Focus on knee recovery");
        TherapyTreatment savedTreatment = therapyTreatmentRepository.save(therapyTreatment);

        // Perform GET request and validate response
        mockMvc.perform(get("/api/treatment/report/therapy/{treatmentId}", savedTreatment.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Treatment Report"))
                .andExpect(jsonPath("$.hospital").value("Ain Shams Charity Hospital"))
                .andExpect(jsonPath("$.patientId").value(savedPatient.getId()))
                .andExpect(jsonPath("$.therapyType").value("Physical Therapy"))
                .andExpect(jsonPath("$.duration").value("14 days"))
                .andExpect(jsonPath("$.frequency").value("Daily"))
                .andExpect(jsonPath("$.notes").value("Focus on knee recovery"))
                .andExpect(jsonPath("$.footer").value("Report Generated Successfully!"));
    }
}
