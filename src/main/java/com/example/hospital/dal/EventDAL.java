package com.example.hospital.dal;

import com.example.hospital.models.Event;
import com.example.hospital.repositories.EventRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDAL {

    private final EventRepository eventRepository;

    public EventDAL(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + id));
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
