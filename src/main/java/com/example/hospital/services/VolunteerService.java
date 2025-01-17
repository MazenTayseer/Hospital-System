package com.example.hospital.services;

import com.example.hospital.dal.VolunteerDAL;
import com.example.hospital.models.Volunteer;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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

    // Handle multiple events
    if (volunteerDto.getEventIds() != null && !volunteerDto.getEventIds().isEmpty()) {
        List<Event> events = volunteerDto.getEventIds().stream()
                .map(eventDAL::findById) // Fetch each event by ID
                .collect(Collectors.toList());
        volunteer.setEvents(events);

        // Add the volunteer to each event's list of volunteers
        events.forEach(event -> event.getVolunteers().add(volunteer));
    }

    return volunteerDAL.save(volunteer); // Save the volunteer and update relationships
}

    public void assignVolunteerToEvent(Long volunteerId, Long eventId) {
        Volunteer volunteer = volunteerDAL.findById(volunteerId);
        Event event = eventDAL.findById(eventId);
    
        // Add event to volunteer's event list
        if (!volunteer.getEvents().contains(event)) {
            volunteer.getEvents().add(event);
        }
    
        // Add volunteer to event's volunteer list
        if (!event.getVolunteers().contains(volunteer)) {
            event.getVolunteers().add(volunteer);
        }
    
        // Save the changes
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
