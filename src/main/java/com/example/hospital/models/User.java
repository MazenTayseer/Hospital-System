package com.example.hospital.models;

import com.example.hospital.models.enums.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The first name is required")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "The last name is required")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "The email is required")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "The password is required")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "The phone is required")
    @Column(unique = true, nullable = false)
    private String phone;

    @NotNull(message = "The age is required")
    @Column(nullable = false)
    private int age;
    
    @NotNull(message = "The gender is required")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "The role is required")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        int age,
        Gender gender
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
    }

    public Long getId() { return this.id; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public Role getRole() { return this.role; }
    public String getEmail() { return this.email; }
    public String getPhone() { return this.phone; }
    public int getAge() { return this.age; }
    public String getPassword() { return this.password; }
    public Gender getGender() { return this.gender; }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
