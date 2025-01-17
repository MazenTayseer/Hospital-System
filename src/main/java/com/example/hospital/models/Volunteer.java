package com.example.hospital.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.example.hospital.models.enums.Gender;

@Entity
@Table(name = "volunteers")
public class Volunteer extends User {

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = true) // Nullable for volunteers who aren't yet assigned to an event
    private Event event;

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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
