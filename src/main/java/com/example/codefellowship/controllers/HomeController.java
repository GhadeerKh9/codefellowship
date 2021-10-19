package com.example.codefellowship.controllers;

import com.example.codefellowship.models.Application;
import com.example.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller

public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal Application user , Model model){
        if (user != null) {
            Application currentUser = applicationUserRepository.findByUsername(user.getUsername());
            model.addAttribute("user", currentUser.getId());
        }
        return "home";
    }

    @GetMapping("/profile")
    public String profile(@RequestParam Integer id , Model model){
        Optional<Application> user =  applicationUserRepository.findById(id);
        model.addAttribute("username", user.get().getUsername());
        model.addAttribute("firstName", user.get().getFirstName());
        model.addAttribute("lastName", user.get().getLastName());
        model.addAttribute("dateOfBirth", user.get().getDateOfBirth());
        model.addAttribute("bio", user.get().getBio());
        return "profile";
    }


}
