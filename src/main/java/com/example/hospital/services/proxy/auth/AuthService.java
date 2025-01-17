package com.example.hospital.services.proxy.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Override
    public String page(String name) {
        return name;
    }
}
