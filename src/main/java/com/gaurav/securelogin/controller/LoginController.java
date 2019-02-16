package com.gaurav.securelogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showHome(){
        return "login";
    }

    @RequestMapping("/forgotPasswordPage")
    public String forgotPasswordPage(){
       return "forgot-password-page";
    }

    @RequestMapping("/errorinpage")
    public String errorinpage(Model model){
        model.addAttribute("error",true);
        return "redirect:/login";
    }
}
