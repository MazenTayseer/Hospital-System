package com.example.hospital.controllers.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.hospital.dal.UserDAL;
import com.example.hospital.models.User;

@Service
public class AuthUser {

    private final UserDAL userDal;

    public AuthUser(UserDAL userDal) {
        this.userDal = userDal;
    }

    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return userDal.findByEmail(username);
        }
        return null;
    }
}
