package com.hotelmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
    @RequestMapping(value="/test/home")
    public String home() {
        return "home";
    }

    @RequestMapping(value="/test/about")
    public String about() {
        return "about";
    }

}
