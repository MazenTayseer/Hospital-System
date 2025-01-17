package com.example.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
import com.example.hospital.models.Room;
import com.example.hospital.repositories.RoomRepository;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                             .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    public Room assignDoctor(Long roomId, Doctor doctor) {
        Room room = roomRepository.findById(roomId)
                                  .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.canAssignDoctor()) {
            throw new IllegalStateException("Room already has a doctor assigned");
        }

        room.setDoctor(doctor);
        return roomRepository.save(room);
    }

    public Room assignNurse(Long roomId, Nurse nurse) {
        Room room = roomRepository.findById(roomId)
                                  .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.canAssignNurse()) {
            throw new IllegalStateException("Room already has 4 nurses assigned");
        }

        room.getNurses().add(nurse);
        return roomRepository.save(room);
    }

    public Room assignPatient(Long roomId, Patient patient) {
        Room room = roomRepository.findById(roomId)
                                  .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.canAssignPatient()) {
            throw new IllegalStateException("Room already has 2 patients assigned");
        }

        // Ensure patient has a surgery
        if (patient.getSurgeryTreatments() == null || patient.getSurgeryTreatments().isEmpty()) {
            throw new IllegalStateException("Only patients with surgeries can be assigned to a room");
        }

        room.getPatients().add(patient);
        return roomRepository.save(room);
    }
}
