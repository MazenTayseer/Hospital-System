package com.example.hospital.dto;

import java.util.List;

import com.example.hospital.models.enums.Gender;

public class VolunteerDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int age;
    private Gender gender;
    private List<Long> eventIds;// Optional: The ID of the event the volunteer wants to join

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public List<Long> getEventIds() { return eventIds; }
    public void setEventIds(List<Long> eventIds) { this.eventIds = eventIds; }
}
