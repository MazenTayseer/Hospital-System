package com.example.hospital.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.hospital.controllers.helpers.AuthUser;
import com.example.hospital.models.Doctor;
import com.example.hospital.models.User;
import com.example.hospital.services.DoctorService;

@Controller
public class _BaseController {
    private final AuthUser authUser;
    private final DoctorService doctorService;

    public _BaseController(AuthUser authUser, DoctorService doctorService) {
        this.authUser = authUser;
        this.doctorService = doctorService;
    }

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

    @GetMapping("/profileDoctor")
    public ModelAndView profilePage() {
        User user = authUser.getLoggedUser();
        ModelAndView mav = new ModelAndView("profileDoctor");
        mav.addObject("user", user); // Add user object to display profile details
        mav.addObject("roles", user.getRolesName());
        return mav;
    }


    @GetMapping("/profilePatient")
    public ModelAndView profilePagePatient() {
        User user = authUser.getLoggedUser();
        ModelAndView mav = new ModelAndView("profilePatient");
        mav.addObject("user", user); // Add user object to display profile details
        mav.addObject("roles", user.getRolesName());
        return mav;
    }

    @GetMapping("/admin")
    public ModelAndView adminPage() {
        User user = authUser.getLoggedUser();
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("loggedUserId", user.getId());
        return mav;
    }

    @GetMapping("/create-user")
    public ModelAndView createUserPage() {
        ModelAndView mav = new ModelAndView("create-user");
        return mav;
    }

    @GetMapping("doctors/{id}")
    public ModelAndView getDoctorDetails(@PathVariable("id") Long id) {
        User user = authUser.getLoggedUser();
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return new ModelAndView("redirect:/error");
        }
        ModelAndView mav = new ModelAndView("doctor-details");
        mav.addObject("doctor", doctor);
        mav.addObject("loggedUserId", user.getId());
        return mav;
    }

    @GetMapping("/send-email")
    public ModelAndView sendEmailPage() {
        ModelAndView mav = new ModelAndView("send-email");
        return mav;
    }

    @GetMapping("/roomsPage")
    public ModelAndView roomPage() {
    User user = authUser.getLoggedUser();
    if (!user.getRolesName().contains("MANAGER")) {
        return new ModelAndView("redirect:/error");
    }

    ModelAndView mav = new ModelAndView("roomsPage");
    return mav;
    }

}