package com.example.hospital.controllers;

import com.example.hospital.dto.VolunteerDto;
import com.example.hospital.models.Event;
import com.example.hospital.models.Volunteer;
import com.example.hospital.services.VolunteerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    public ResponseEntity<Volunteer> registerVolunteer(@RequestBody VolunteerDto volunteerDto) {
        Volunteer volunteer = volunteerService.registerVolunteer(volunteerDto);
        return ResponseEntity.ok(volunteer);
    }

    @PostMapping("/{volunteerId}/join-event/{eventId}")
    public ResponseEntity<String> assignVolunteerToEvent(
        @PathVariable Long volunteerId,
        @PathVariable Long eventId) {
    volunteerService.assignVolunteerToEvent(volunteerId, eventId);
    return ResponseEntity.ok("Volunteer has been successfully assigned to the event.");
}


    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable Long id) {
        Volunteer volunteer = volunteerService.getVolunteerById(id);
        return ResponseEntity.ok(volunteer);
    }

    @GetMapping
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        return ResponseEntity.ok(volunteers);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Volunteer>> getVolunteersForEvent(@PathVariable Long eventId) {
        List<Volunteer> volunteers = volunteerService.getVolunteersForEvent(eventId);
        return ResponseEntity.ok(volunteers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok("Volunteer deleted successfully.");
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = volunteerService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{volunteerId}/events")
    public ResponseEntity<List<Event>> getEventsForVolunteer(@PathVariable Long volunteerId) {
        List<Event> events = volunteerService.getEventsForVolunteer(volunteerId);
        return ResponseEntity.ok(events);
    }
}
