package com.example.hospital.services;

import com.example.hospital.dal.UserDAL;
import com.example.hospital.models.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAL userDAL;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAL.findByEmail(email);

        List<String> userRoles = new ArrayList<>();
        user.getRoles().forEach(
            (role) -> {
                userRoles.add(role.getName());
            }
        );

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .roles(userRoles.toArray(new String[0]))
            .build();
    }
}
