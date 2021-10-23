package com.example.codefellowship.controllers;

import com.example.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class mainController {


    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String homePage(Principal principal, Model model){

            model.addAttribute("username",principal.getName());

        return "home";
    }
}
