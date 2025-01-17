package com.example.hospital.services;

import org.springframework.stereotype.Service;

import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.dal.NurseDAL;
import com.example.hospital.dal.PatientDAL;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
import com.example.hospital.models.Room;
import com.example.hospital.repositories.RoomRepository;

import java.util.List;

@Service
public class RoomService {

    private RoomRepository roomRepository;
    private NurseDAL nurseDAL;
    private DoctorDAL doctorDAL;
    private PatientDAL patientDAL;

    public RoomService(RoomRepository roomRepository, NurseDAL nurseDAL, DoctorDAL doctorDAL, PatientDAL patientDAL) {
        this.roomRepository = roomRepository;
        this.nurseDAL = nurseDAL;
        this.doctorDAL = doctorDAL;
        this.patientDAL = patientDAL;
    }

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

    public Room assignDoctor(Long roomId, Long doctorId) {
        Doctor doctor = doctorDAL.findById(doctorId);
        Room room = roomRepository.findById(roomId)
                                  .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.canAssignDoctor()) {
            throw new BadRequestException("Room already has a doctor assigned");
        }

        room.setDoctor(doctor);
        return roomRepository.save(room);
    }

    public Room assignNurse(Long roomId, Long nurseId) {
        Nurse nurse = nurseDAL.findById(nurseId);
        Room room = roomRepository.findById(roomId)
                                  .orElseThrow(() -> new RuntimeException("Room not found"));
    
        if (room.getNurses().contains(nurse)) {
            throw new BadRequestException("Nurse is already assigned to this room");
        }
    
        if (!room.canAssignNurse()) {
            throw new BadRequestException("Room already has 4 nurses assigned");
        }
    
        room.getNurses().add(nurse);
        return roomRepository.save(room);
    }
    

    public Room assignPatient(Long roomId, Long patientId) {
        Patient patient = patientDAL.findById(patientId);
        Room room = roomRepository.findById(roomId)
                                  .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.canAssignPatient()) {
            throw new BadRequestException("Room already has 2 patients assigned");
        }

        if (patient.getSurgeryTreatments() == null || patient.getSurgeryTreatments().isEmpty()) {
            throw new BadRequestException("Only patients with surgeries can be assigned to a room");
        }

        room.getPatients().add(patient);
        return roomRepository.save(room);
    }
}
