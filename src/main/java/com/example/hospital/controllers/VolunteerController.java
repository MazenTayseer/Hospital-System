package com.example.hospital.controllers;

import com.example.hospital.models.Volunteer;
import com.example.hospital.services.VolunteerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Volunteer> addVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer savedVolunteer = volunteerService.saveVolunteer(volunteer);
        return ResponseEntity.ok(savedVolunteer);
    }

    @DeleteMapping("/delete/{volunteerId}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long volunteerId) {
        volunteerService.deleteVolunteer(volunteerId);
        return ResponseEntity.noContent().build(); // Return 204 No Content status
    }

    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable Long id) {
        Volunteer volunteer = volunteerService.getVolunteer(id);
        return ResponseEntity.ok(volunteer);
    }

    @GetMapping
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        return ResponseEntity.ok(volunteers);
    }

}
