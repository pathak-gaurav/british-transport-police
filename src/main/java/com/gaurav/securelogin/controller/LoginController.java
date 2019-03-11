package com.gaurav.securelogin.controller;

import com.gaurav.securelogin.entity.OfficerDetails;
import com.gaurav.securelogin.entity.User;
import com.gaurav.securelogin.repository.UserRepository;
import com.gaurav.securelogin.service.CustomUserServiceImpl;
import com.gaurav.securelogin.service.CustomUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Slf4j
public class LoginController {

    private UserRepository userRepository;
    private CustomUsersService customUsersService;

    public LoginController(UserRepository userRepository, CustomUsersService customUsersService) {
        this.userRepository = userRepository;
        this.customUsersService = customUsersService;
    }

    @RequestMapping("/login")
    public String showHome() {
        CustomUserServiceImpl.tempPassword = null;
        return "login";
    }

    @RequestMapping("/forgotPasswordPage")
    public String forgotPasswordPage() {
        return "forgot-password-page";
    }

    @PostMapping("/forgotPasswordProcessing")
    public String forgotPasswordProcessing(@RequestParam("username") String username, Model model) {
        User user = customUsersService.forgotPassword(username).orElse(null);
        if (user == null) {
            return "/forgotPasswordPage?invalidUsername";
        } else {
            return "redirect:/login?passwordChanged="+CustomUserServiceImpl.tempPassword;
        }
    }

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "access-denied";
    }

    @RequestMapping("/signupForm")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user, Model model) {
        System.out.println(user.getUsername() + "\t" + user.getPassword());
        user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setDisabled("true");
        user.setOfficerDetails(new OfficerDetails("Dummy", "Dummy"));
        userRepository.save(user);
        model.addAttribute("signup", "User has been Added");
        return "redirect:/login?xksitup=x";

    }
}
