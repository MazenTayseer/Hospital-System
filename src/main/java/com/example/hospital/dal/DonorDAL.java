package com.example.hospital.dal;

import org.springframework.stereotype.Component;

import com.example.hospital.ResponseMessages;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Donor;
import com.example.hospital.repositories.DonorRepository;


@Component
public class DonorDAL {

    private final DonorRepository donorRepository;

    public DonorDAL(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public Donor findById(Long id) {
        return donorRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ResponseMessages.record_not_found("Donor"))
        );
    }

    public Donor save(Donor donor) {
        return donorRepository.save(donor);
    }

    public void deleteAll() {
        donorRepository.deleteAll();
    }

    public void delete(Donor donor) {
        donorRepository.delete(donor);
    }
}