package com.example.sampleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SinglePageController {
	@GetMapping("https://fierce-forest-67177.herokuapp.com/**/{path:[^.]*}")
    public String any() {
        return "forward:/index.html";
    }
}