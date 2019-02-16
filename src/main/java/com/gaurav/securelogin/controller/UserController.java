package com.gaurav.securelogin.controller;

import com.gaurav.securelogin.repository.OfficerDetailsRepository;
import com.gaurav.securelogin.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserRepository userRepository;
    private OfficerDetailsRepository officerDetailsRepository;

    public UserController(UserRepository userRepository, OfficerDetailsRepository officerDetailsRepository) {
        this.userRepository = userRepository;
        this.officerDetailsRepository = officerDetailsRepository;
    }

    @RequestMapping("/profile")
    public String showProfile(@RequestParam("username") String username, Model model) {
        model.addAttribute("users", userRepository.findById(username).orElse(null));
        return "profile";
    }

    @RequestMapping("/careers")
    public String career() {
        return "career";
    }
}
