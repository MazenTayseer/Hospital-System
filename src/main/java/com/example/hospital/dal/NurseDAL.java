package com.example.hospital.dal;

import com.example.hospital.ResponseMessages;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Nurse;

import java.util.List;

import org.springframework.stereotype.Component;
import com.example.hospital.repositories.NurseRepository;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.ResponseMessages;
import java.util.List;

@Component
public class NurseDAL {

    private final NurseRepository nurseRepository;

    public NurseDAL(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    public Nurse save(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public void deleteAll() {
        nurseRepository.deleteAll();
    }

    public List<Nurse> findAll() {
        return nurseRepository.findAll();
    }

    public Nurse findById(Long id) {
        return nurseRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ResponseMessages.record_not_found("Nurse"))
        );
    }

    public void delete(Nurse nurse) {
        nurseRepository.delete(nurse);
    }
}
