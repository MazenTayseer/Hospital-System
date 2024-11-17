package com.example.hospital.dal;

import com.example.hospital.ResponseMessages;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Volunteer;
import org.springframework.stereotype.Component;
import com.example.hospital.repositories.VolunteerRepository;

import java.util.List;

@Component
public class VolunteerDAL {

    private final VolunteerRepository volunteerRepository;

    public VolunteerDAL(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public Volunteer findById(Long id) {
        return volunteerRepository.findById(id).orElseThrow(
            () -> new BadRequestException(ResponseMessages.record_not_found("Volunteer"))
        );
    }

    public Volunteer save(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    // Add this method to enable findAll() functionality
    public List<Volunteer> findAll() {
        return volunteerRepository.findAll();
    }
}
