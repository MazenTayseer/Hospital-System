package com.example.hospital.services.proxy.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceProxy implements IAuthService {

    @Autowired
    private AuthService authService;

    @Override
    public String page(String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
            return "redirect:/home";
        }
        return authService.page(name);
    }
}
