package com.umc.bobmate.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class IndexController implements ErrorController {

    private static final String path = "/kakaoLogin";
    @RequestMapping(value=path)
    public ModelAndView saveLeadQuery(){
        return new ModelAndView("forward:/");
    }

}
