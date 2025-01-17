package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
import com.example.hospital.models.Room;
import com.example.hospital.services.RoomService;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room createdRoom = roomService.createRoom(room);
        return ResponseEntity.ok(createdRoom);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{roomId}/assign-doctor")
    public ResponseEntity<Room> assignDoctor(@PathVariable Long roomId, @RequestBody Doctor doctor) {
        Room updatedRoom = roomService.assignDoctor(roomId, doctor);
        return ResponseEntity.ok(updatedRoom);
    }

    @PostMapping("/{roomId}/assign-nurse")
    public ResponseEntity<Room> assignNurse(@PathVariable Long roomId, @RequestBody Nurse nurse) {
        Room updatedRoom = roomService.assignNurse(roomId, nurse);
        return ResponseEntity.ok(updatedRoom);
    }

    @PostMapping("/{roomId}/assign-patient")
    public ResponseEntity<Room> assignPatient(@PathVariable Long roomId, @RequestBody Patient patient) {
        Room updatedRoom = roomService.assignPatient(roomId, patient);
        return ResponseEntity.ok(updatedRoom);
    }

    @PostMapping("/{roomId}/unassign-patient")
    public ResponseEntity<Room> unassignPatient(@PathVariable Long roomId, @RequestBody Patient patient) {
        Room updatedRoom = roomService.assignPatient(roomId, patient);
        return ResponseEntity.ok(updatedRoom);
    }
}
