package com.example.codefellowship.controllers;

import com.example.codefellowship.models.Application;
import com.example.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String getSignup(){
        return "signup";
    }


    @PostMapping("/signup")
    public RedirectView signupUser(@ModelAttribute Application user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
        return new RedirectView ("/login");
    }


    @GetMapping("/login")
    public String getLoginPage(Principal p, Model model){
        try{
            model.addAttribute("userData",p.getName());
        }catch (NullPointerException e){
            model.addAttribute("userData","");
        }
        return "login.html";
    }

}