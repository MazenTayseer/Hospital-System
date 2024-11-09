package com.example.hospital.services;

import org.springframework.stereotype.Service;

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

    public User createUser(User user) {
        return createUserContext.createUser(user);
    }

        public void deleteUser(Long userId) {
        User user = userDAL.findById(userId);

        if (user == null) {
            throw new BadRequestException(ResponseMessages.record_not_found("User"));
        }

        userDAL.delete(user);
    }
}
