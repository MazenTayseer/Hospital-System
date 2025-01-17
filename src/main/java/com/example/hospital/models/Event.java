package com.example.hospital.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Event name is required")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Event description is required")
    @Column(nullable = false, length = 500)
    private String description;

    @NotNull(message = "Event date and time is required")
    @Future(message = "Event date must be in the future")
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @NotNull(message = "Event location is required")
    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Volunteer> volunteers;

    public Event() {}

    public Event(String name, String description, LocalDateTime dateTime, String location) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }
}
