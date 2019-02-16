package com.gaurav.securelogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String showHome(Model model){
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        return "about-us";
    }
}
