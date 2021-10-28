package com.example.codefellowship.controllers;

import com.example.codefellowship.models.Application;
import com.example.codefellowship.models.Post;
import com.example.codefellowship.repositories.ApplicationUserRepository;
import com.example.codefellowship.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Set;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    PostRepository postRepository;


    @GetMapping("/myprofile")
    public String UserProfile(Principal principal , Model model) {
        if (applicationUserRepository != null) {
            model.addAttribute("userData", principal.getName());
            model.addAttribute("allUserData", applicationUserRepository.findByUsername(principal.getName()));
        } else {
            model.addAttribute("userData", "No user");
            model.addAttribute("allUserData", new Application());
        }
        return "user";
    }

    @PostMapping("/myprofile")
    public RedirectView addNewPost(Principal principal , @RequestParam String body){
        Post post = new Post(body , applicationUserRepository.findByUsername(principal.getName()));
        postRepository.save(post);
        return new RedirectView("/myprofile");
    }

//////////////////////////////////////////////
    @GetMapping("/allUsers")
    public String getAllUsers(Principal principal,Model model){
        try{
            model.addAttribute("userData",principal.getName());
            model.addAttribute("AllUsers",applicationUserRepository.findAll());

            Application user = applicationUserRepository.findByUsername(principal.getName());
            model.addAttribute("userFollow",user.getFollowers());
        }catch (NullPointerException e){
            model.addAttribute("userData","");
        }
        return "allUsers";
    }


    @GetMapping



    @PostMapping("/follow")
    public RedirectView addFollow(Principal principal, @RequestParam int id){
        Application user = applicationUserRepository.findByUsername(principal.getName());
        Application toFollow = applicationUserRepository.findById(id).get();
        user.getFollowers().add(toFollow);

        applicationUserRepository.save(user);
        return new RedirectView("/feed");
    }


    @GetMapping("/feed")
    public String getFollowingInfo(Principal principal, Model model){
        try{
            model.addAttribute("userData",principal.getName());
            Application user = applicationUserRepository.findByUsername(principal.getName());

            Set<Application> userFollow = user.getFollowers();

            model.addAttribute("Allfollowing",userFollow);
        }catch (NullPointerException e){
            model.addAttribute("userData","");
        }
        return "feed.html";
    }


}