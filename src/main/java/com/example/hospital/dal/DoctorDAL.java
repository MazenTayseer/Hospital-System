package com.example.hospital.dal;

import java.util.List;

import com.example.hospital.ResponseMessages;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.enums.Speciality;
import com.example.hospital.repositories.DoctorRepository;
import org.springframework.stereotype.Component;

@Component 
public class DoctorDAL {
    private final DoctorRepository doctorRepository;

    public DoctorDAL(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findDoctorsBySpeciality(Speciality speciality) {
        return doctorRepository.findBySpeciality(speciality);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ResponseMessages.record_not_found("Doctor"))
        );
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteAll() {
        doctorRepository.deleteAll();
    }

    public long count() {
        return doctorRepository.count();
    }
}
