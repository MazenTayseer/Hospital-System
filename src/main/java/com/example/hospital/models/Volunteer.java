package com.example.hospital.models;

import com.example.hospital.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import com.example.hospital.models.enums.Gender;

import java.util.List;

@Entity
@Table(name = "volunteers")
public class Volunteer extends User {

    @ManyToMany
    @JsonIgnore
    @JoinTable(
        name = "volunteer_events",
        joinColumns = @JoinColumn(name = "volunteer_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    public Volunteer() {}

    public Volunteer(
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            int age,
            Gender gender
    ) {
        super(firstName, lastName, email, password, phone, age, gender);
    }

    // Getters and Setters
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


}
