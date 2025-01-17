package com.example.hospital.controllers;

import com.example.hospital.models.MedicationTreatment;
import com.example.hospital.models.SurgeryTreatment;
import com.example.hospital.models.TherapyTreatment;
import com.example.hospital.models.dto.MedicationTreatmentDTO;
import com.example.hospital.models.dto.SurgeryTreatmentDTO;
import com.example.hospital.models.dto.TherapyTreatmentDTO;
import com.example.hospital.models.request.TreatmentRequest;
import com.example.hospital.services.PatientTreatmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/treatments")
public class PatientTreatmentViewController {

    private final PatientTreatmentService treatmentService;

    public PatientTreatmentViewController(PatientTreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    // Render the patient selection form
    @GetMapping("/select-patient")
    public String showSelectPatientForm() {
        return "select-patient";
    }

    // Handle patient selection and redirect to the selected treatment type
    @PostMapping("/select-patient")
    public String handlePatientSelection(
            @RequestParam Long patientId,
            @RequestParam String treatmentType,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("patientId", patientId);
        return "redirect:/treatments/patient/" + patientId + "/" + treatmentType;
    }

   // Render the add medication treatment form
   @GetMapping("/add/medication")
   public String showAddMedicationForm() {
       return "add-medication-treatment";
   }

   // Handle form submission for adding a medication treatment
   @PostMapping("/add/medication")
   public String addMedicationTreatment(@ModelAttribute TreatmentRequest treatmentRequest) {
       treatmentService.assignMedicationTreatmentToPatient(treatmentRequest);
       return "redirect:/treatments/patient/" + treatmentRequest.getPatientId() + "/medications";
   }

   // View all medication treatments for a patient
   @GetMapping("/patient/{patientId}/medications")
   public String viewMedicationTreatments(@PathVariable Long patientId, Model model) {
       List<MedicationTreatmentDTO> treatments = treatmentService.getMedicationTreatmentsForPatient(patientId);
       model.addAttribute("treatments", treatments);
       model.addAttribute("patientId", patientId); // Pass patientId to the template
       return "view-medication-treatments";
   }
// Render the add surgery treatment form
@GetMapping("/add/surgery")
public String showAddSurgeryForm() {
    return "add-surgery-treatment";
}

// Handle form submission for adding a surgery treatment
@PostMapping("/add/surgery")
public String addSurgeryTreatment(@ModelAttribute TreatmentRequest treatmentRequest) {
    treatmentService.assignSurgeryTreatmentToPatient(treatmentRequest);
    return "redirect:/treatments/patient/" + treatmentRequest.getPatientId() + "/surgeries";
}

// Render the add therapy treatment form
@GetMapping("/add/therapy")
public String showAddTherapyForm() {
    return "add-therapy-treatment";
}

// Handle form submission for adding a therapy treatment
@PostMapping("/add/therapy")
public String addTherapyTreatment(@ModelAttribute TreatmentRequest treatmentRequest) {
    treatmentService.assignTherapyTreatmentToPatient(treatmentRequest);
    return "redirect:/treatments/patient/" + treatmentRequest.getPatientId() + "/therapies";
}

// View all surgery treatments for a patient
@GetMapping("/patient/{patientId}/surgeries")
public String viewSurgeryTreatments(@PathVariable Long patientId, Model model) {
    List<SurgeryTreatmentDTO> treatments = treatmentService.getSurgeryTreatmentsForPatient(patientId);
    model.addAttribute("treatments", treatments);
    model.addAttribute("patientId", patientId); // Pass patientId to the template
    return "view-surgery-treatments";
}

// View all therapy treatments for a patient
@GetMapping("/patient/{patientId}/therapies")
public String viewTherapyTreatments(@PathVariable Long patientId, Model model) {
    List<TherapyTreatmentDTO> treatments = treatmentService.getTherapyTreatmentsForPatient(patientId);
    model.addAttribute("treatments", treatments);
    model.addAttribute("patientId", patientId); // Pass patientId to the template
    return "view-therapy-treatments";
}

@GetMapping("/add/reports")
public String showReportPage() {
    return "reports";
}

@GetMapping("/report")
public String getTreatmentReport(
        @RequestParam String treatmentType,
        @RequestParam Long treatmentId,
        Model model) {
    Map<String, Object> report;
    switch (treatmentType.toLowerCase()) {
        case "medication":
            MedicationTreatment medication = treatmentService.getMedicationTreatmentById(treatmentId);
            report = Map.of(
                "Treatment Type", "Medication",
                "Medication Name", medication.getMedicationName(),
                "Dosage", medication.getDosage(),
                "Duration", medication.getDuration() + " days",
                "Frequency", medication.getFrequency()
            );
            break;
        case "surgery":
            SurgeryTreatment surgery = treatmentService.getSurgeryTreatmentById(treatmentId);
            report = Map.of(
                "Treatment Type", "Surgery",
                "Surgery Type", surgery.getSurgeryType(),
                "Location", surgery.getLocation(),
                "Surgeon", surgery.getSurgeon(),
                "Date", surgery.getDate()
            );
            break;
        case "therapy":
            TherapyTreatment therapy = treatmentService.getTherapyTreatmentById(treatmentId);
            report = Map.of(
                "Treatment Type", "Therapy",
                "Therapy Type", therapy.getTherapyType(),
                "Duration", therapy.getDuration() + " days",
                "Frequency", therapy.getFrequency(),
                "Notes", therapy.getNotes()
            );
            break;
        default:
            throw new IllegalArgumentException("Invalid treatment type");
    }
    model.addAttribute("report", report);
    return "reports";
}

}
