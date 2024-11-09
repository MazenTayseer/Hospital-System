package com.example.hospital.services;

import com.example.hospital.ResponseMessages;
import com.example.hospital.dal.VolunteerDAL;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Volunteer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerService {
    private final VolunteerDAL volunteerDAL;

    public VolunteerService(VolunteerDAL volunteerDAL) {
        this.volunteerDAL = volunteerDAL;
    }

    public Volunteer getVolunteer(Long volunteerId) {
        return volunteerDAL.findById(volunteerId);
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerDAL.findAll();
    }

}
