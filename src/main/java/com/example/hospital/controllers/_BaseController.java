package com.example.hospital.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.hospital.controllers.helpers.AuthUser;
import com.example.hospital.models.User;

@Controller
public class _BaseController {
    @Autowired
    private AuthUser authUser;

    @GetMapping("/home")
    public ModelAndView homePage() {
        User user = authUser.getLoggedUser();
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("fullName", user.getFullName());
        mav.addObject("roles", user.getRolesName());
        return mav;
    }

    @GetMapping("/doctors")
    public ModelAndView doctorsPage() {
        ModelAndView mav = new ModelAndView("doctors");
        return mav;
    }

    @GetMapping("/admin")
    public ModelAndView adminPage() {
        User user = authUser.getLoggedUser();
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("loggedUserId", user.getId());
        return mav;
    }
}