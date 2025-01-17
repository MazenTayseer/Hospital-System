package com.example.hospital.services;

import com.example.hospital.dal.EventDAL;
import com.example.hospital.dto.EventDto;
import com.example.hospital.models.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventDAL eventDAL;

    public EventService(EventDAL eventDAL) {
        this.eventDAL = eventDAL;
    }

    public Event createEvent(EventDto eventDto) {
        Event event = new Event(
                eventDto.getName(),
                eventDto.getDescription(),
                eventDto.getDateTime(),
                eventDto.getLocation()
        );
        return eventDAL.save(event);
    }

    public Event getEventById(Long id) {
        return eventDAL.findById(id);
    }

    public List<Event> getAllEvents() {
        return eventDAL.findAll();
    }

    public void deleteEvent(Long id) {
        eventDAL.deleteById(id);
    }

    public Event updateEvent(Long id, EventDto eventDto) {
        Event event = eventDAL.findById(id);
    
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setDateTime(eventDto.getDateTime());
        event.setLocation(eventDto.getLocation());
    
        return eventDAL.save(event);
    }
}
