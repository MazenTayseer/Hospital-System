package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.hospital.services.proxy.auth.AuthServiceProxy;

@Controller
public class _AuthController  {
    @Autowired
    private AuthServiceProxy authServiceProxy;

    @GetMapping("/login")
    public String loginPage() {
        return authServiceProxy.page("login");
    }
}