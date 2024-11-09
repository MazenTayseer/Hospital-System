package com.example.hospital.services;

import com.example.hospital.ResponseMessages;
import com.example.hospital.dal.VolunteerDAL;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Volunteer;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class VolunteerService {
    private final VolunteerDAL volunteerDAL;

    public VolunteerService(VolunteerDAL volunteerDAL) {
        this.volunteerDAL = volunteerDAL;
    }

    @Transactional
    public Volunteer saveVolunteer(Volunteer volunteer) {
        // You can add any validation or processing logic here if needed
        return volunteerDAL.save(volunteer);
    }

    public void deleteVolunteer(Long volunteerId) {
        Volunteer volunteer = volunteerDAL.findById(volunteerId);

        if (volunteer == null) {
            throw new BadRequestException(ResponseMessages.record_not_found("Volunteer"));
        }

        volunteerDAL.delete(volunteer);
    }

    public Volunteer getVolunteer(Long volunteerId) {
        return volunteerDAL.findById(volunteerId);
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerDAL.findAll();
    }

}
