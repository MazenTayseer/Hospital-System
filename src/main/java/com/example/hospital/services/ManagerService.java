package com.example.hospital.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hospital.dto.UserDto;
import com.example.hospital.ResponseMessages;
import com.example.hospital.dal.UserDAL;
import com.example.hospital.exceptions.BadRequestException;
import com.example.hospital.models.User;
import com.example.hospital.services.strategy.create_user.CreateUserContext;

@Service
public class ManagerService {
    private final CreateUserContext createUserContext;
    private final UserDAL userDAL;

    public ManagerService(CreateUserContext createUserContext, UserDAL userDAL) {
        this.createUserContext = createUserContext;
        this.userDAL = userDAL;
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
}
