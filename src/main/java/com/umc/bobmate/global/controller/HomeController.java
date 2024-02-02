package com.umc.bobmate.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {
    @GetMapping("/index")
    public String home() {
        return "index";
    }
}