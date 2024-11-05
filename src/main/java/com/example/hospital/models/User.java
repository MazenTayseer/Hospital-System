package com.example.hospital.models;

import com.example.hospital.models.enums.*;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    
    @SuppressWarnings("unused")
    private String password;

    private String phone;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public Long getId() { return this.id; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public Role getRole() { return this.role; }
    public String getEmail() { return this.email; }
    public String getPhone() { return this.phone; }
    public int getAge() { return this.age; }

    public void setRole(Role role) {
        this.role = role;
    }
}