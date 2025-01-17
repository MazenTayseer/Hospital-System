package com.example.hospital.controllers;

import com.example.hospital.ResponseWrapper;
import com.example.hospital.dto.UserDto;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.Donor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
import com.example.hospital.models.User;
import com.example.hospital.models.Volunteer;
import com.example.hospital.services.ManagerService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = managerService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create-doctor")
    public ResponseEntity<ResponseWrapper<Doctor>> createDoctor(@RequestBody UserDto<Doctor> request) {
        try {
            Doctor createdUser = (Doctor) managerService.createUser(request);
            return ResponseEntity.ok(new ResponseWrapper<>(createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseWrapper<>("Error creating doctor: " + e.getMessage()));
        }
    }

    @PostMapping("/create-nurse")
    public ResponseEntity<ResponseWrapper<Nurse>> createNurse(@RequestBody UserDto<Nurse> request) {
        try {
            Nurse createdUser = (Nurse) managerService.createUser(request);
            return ResponseEntity.ok(new ResponseWrapper<>(createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseWrapper<>("Error creating nurse: " + e.getMessage()));
        }
    }

    @PostMapping("/create-patient")
    public ResponseEntity<ResponseWrapper<Patient>> createPatient(@RequestBody UserDto<Patient> request) {
        try {
            Patient createdUser = (Patient) managerService.createUser(request);
            return ResponseEntity.ok(new ResponseWrapper<>(createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseWrapper<>("Error creating patient: " + e.getMessage()));
        }
    }

    @PostMapping("/create-volunteer")
    public ResponseEntity<ResponseWrapper<Volunteer>> createVolunteer(@RequestBody UserDto<Volunteer> request) {
        try {
            Volunteer createdUser = (Volunteer) managerService.createUser(request);
            return ResponseEntity.ok(new ResponseWrapper<>(createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseWrapper<>("Error creating volunteer: " + e.getMessage()));
        }
    }

    @PostMapping("/create-donor")
    public ResponseEntity<ResponseWrapper<Donor>> createDonor(@RequestBody UserDto<Donor> request) {
        try {
            Donor createdUser = (Donor) managerService.createUser(request);
            return ResponseEntity.ok(new ResponseWrapper<>(createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>("Error creating Donor: " + e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        managerService.deleteUser(userId);
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long userId,
            @RequestBody UserDto<User> userDto) {
        User updatedUser = managerService.updateUser(userId, userDto.getUser());
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getDoctors() {
        List<Doctor> doctors = managerService.getDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/nurses")
    public ResponseEntity<List<Nurse>> getNurses() {
        List<Nurse> nurses = managerService.getNurses();
        return ResponseEntity.ok(nurses);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getPatients() {
        List<Patient> patients = managerService.getPatients();
        return ResponseEntity.ok(patients);
    }
}
