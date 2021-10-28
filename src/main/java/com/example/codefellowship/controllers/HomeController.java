package com.example.codefellowship.controllers;

import com.example.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {


    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @GetMapping("/")
    public String getHome(Principal principal, Model model){
        try{
            model.addAttribute("username",principal.getName());
        }catch (NullPointerException e){
            model.addAttribute("username","No user");
        }
        return "home";
    }
}
