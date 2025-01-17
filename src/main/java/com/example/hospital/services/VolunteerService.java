package com.example.hospital.services;

import com.example.hospital.dal.VolunteerDAL;
import com.example.hospital.models.Volunteer;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.hospital.dal.EventDAL;
import com.example.hospital.dto.VolunteerDto;
import com.example.hospital.models.Event;

@Service
public class VolunteerService {

    private final VolunteerDAL volunteerDAL;
    private final EventDAL eventDAL;

    public VolunteerService(VolunteerDAL volunteerDAL, EventDAL eventDAL) {
        this.volunteerDAL = volunteerDAL;
        this.eventDAL = eventDAL;
    }

    public Volunteer registerVolunteer(VolunteerDto volunteerDto) {
        Volunteer volunteer = new Volunteer(
                volunteerDto.getFirstName(),
                volunteerDto.getLastName(),
                volunteerDto.getEmail(),
                volunteerDto.getPassword(),
                volunteerDto.getPhone(),
                volunteerDto.getAge(),
                volunteerDto.getGender()
        );

        if (volunteerDto.getEventId() != null) {
            Event event = eventDAL.findById(volunteerDto.getEventId());
            volunteer.setEvent(event);
        }

        return volunteerDAL.save(volunteer);
    }

    public void assignVolunteerToEvent(Long volunteerId, Long eventId) {
        Volunteer volunteer = volunteerDAL.findById(volunteerId);
        Event event = eventDAL.findById(eventId);

        volunteer.setEvent(event);
        volunteerDAL.save(volunteer);
    }
    
    public List<Volunteer> getVolunteersForEvent(Long eventId) {
        return volunteerDAL.findByEventId(eventId);
    }

    public Volunteer getVolunteerById(Long id) {
        return volunteerDAL.findById(id);
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerDAL.findAll();
    }

    public void deleteVolunteer(Long id) {
        volunteerDAL.deleteById(id);
    }
}
