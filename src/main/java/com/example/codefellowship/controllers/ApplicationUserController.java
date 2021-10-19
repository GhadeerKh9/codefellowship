package com.example.codefellowship.controllers;

import com.example.codefellowship.models.Application;
import com.example.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String getSignInPage() {
        return "signin";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "dateOfBirth") String dateOfBirth, @RequestParam(value = "bio") String bio) {
        Application newUser = new Application(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/login");
    }

}
