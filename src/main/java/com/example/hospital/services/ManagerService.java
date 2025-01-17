package com.example.hospital.services;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hospital.dto.UserDto;
import com.example.hospital.ResponseMessages;
import com.example.hospital.dal.DoctorDAL;
import com.example.hospital.dal.NurseDAL;
import com.example.hospital.dal.PatientDAL;
import com.example.hospital.dal.UserDAL;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.Nurse;
import com.example.hospital.models.Patient;
import com.example.hospital.models.User;
import com.example.hospital.services.strategy.create_user.CreateUserContext;

@Service
public class ManagerService {
    private final CreateUserContext createUserContext;
    private final UserDAL userDAL;
    private final DoctorDAL doctorDAL;
    private final NurseDAL nurseDAL;
    private final PatientDAL patientDAL;

    public ManagerService(CreateUserContext createUserContext, UserDAL userDAL, DoctorDAL doctorDAL, NurseDAL nurseDAL, PatientDAL patientDAL) {
        this.createUserContext = createUserContext;
        this.userDAL = userDAL;
        this.doctorDAL = doctorDAL;
        this.nurseDAL = nurseDAL;
        this.patientDAL = patientDAL;
    }

    public List<User> getAllUsers() {
        return userDAL.findAll();
    }

    public User createUser(UserDto<? extends User> request) {
        return createUserContext.createUser(request);
    }

    public void deleteUser(Long userId) {
        User user = userDAL.findById(userId);

        if (user == null) {
            throw new BadRequestException(ResponseMessages.record_not_found("User"));
        }

        userDAL.delete(user);
    }

    @Transactional
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userDAL.findById(userId);
        

            // Update user fields
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setAge(updatedUser.getAge());
            existingUser.setGender(updatedUser.getGender());
            existingUser.setPassword(updatedUser.getPassword());

            return userDAL.save(existingUser);

    }

    public List<Doctor> getDoctors() {
        return doctorDAL.findAllDoctors();
    }

    public List<Nurse> getNurses() {
        return nurseDAL.findAll();
    }

    public List<Patient> getPatients() {
        return patientDAL.findAll();
    }
}
