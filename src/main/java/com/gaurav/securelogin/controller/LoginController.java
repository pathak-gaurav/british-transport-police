package com.gaurav.securelogin.controller;

import com.gaurav.securelogin.entity.OfficerDetails;
import com.gaurav.securelogin.entity.User;
import com.gaurav.securelogin.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/login")
    public String showHome() {
        return "login";
    }

    @RequestMapping("/forgotPasswordPage")
    public String forgotPasswordPage() {
        return "forgot-password-page";
    }

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "access-denied";
    }

    @RequestMapping("/signupForm")
    public String signupForm(Model model){
        model.addAttribute("user",new User());
        return "sign-up";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user, Model model) {
        System.out.println(user.getUsername()+"\t"+user.getPassword());
        user.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setDisabled("true");
        user.setOfficerDetails(new OfficerDetails("Dummy", "Dummy"));
        userRepository.save(user);
        model.addAttribute("signup","User has been Added");
        return "redirect:/login";

    }
}
